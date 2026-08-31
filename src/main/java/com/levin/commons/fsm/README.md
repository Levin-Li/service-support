# 有限状态机（FSM）

本包提供一个轻量的有限状态机模型。状态集合由 `FSM` 统一提供；每个状态声明从自身出发的流转规则；`FSMHelper`
负责创建默认事件、默认规则，以及查询某状态当前可以触发的事件。

```text
当前状态 --(事件 + 附加触发条件)--> 目标状态
```

## 核心模型

| 类型                                     | 职责                                           |
|----------------------------------------|----------------------------------------------|
| `FSM<EVENT>`                           | 一个状态机定义；`allStates()` 返回全部有限状态。实现类建议使用枚举。    |
| `FsmState<EVENT>`                      | 一个状态；`transitionRules()` 返回以当前状态为源状态的出向流转规则。 |
| `FsmStateTransitionRule<EVENT, STATE>` | 一条完整规则：源状态、事件、目标状态。                          |
| `FsmEvent`                             | 事件元数据契约：名称、描述、来源。业务事件通常用枚举实现。                |
| `FsmEventSource`                       | 事件来源：用户、管理、系统、定时、外部、消息、其他。                   |
| `FSMHelper`                            | 默认事件/规则创建，以及可触发事件查询工具。                       |

## 有限状态集合

状态必须是有限集合；实际设计中推荐让状态枚举实现 `FsmState`，并由状态机实现的 `allStates()` 返回全部枚举值。

```java
public interface FSM<EVENT> {
    List<FsmState<EVENT>> allStates();
}
```

`allStates()` 是状态机的全量状态目录。调用方不应只依据局部规则推测状态集合。

## 状态与出向规则

`FsmState.transitionRules()` 表示“**以当前状态为源状态**”的规则集合，而不是进入当前状态的规则集合。

```java
public interface FsmState<EVENT> {
    String name();

    List<? extends FsmStateTransitionRule<EVENT, FsmState<EVENT>>>
            transitionRules();
}
```

每条 `FsmStateTransitionRule` 包含：

```java
STATE sourceState();
EVENT event();
STATE targetState();
```

- `sourceState` 可在规则接口中为 `null`，用于描述从初始空状态出发的规则。
- `event` 不可为空。
- `targetState` 不可为空。

## 创建事件与规则

默认事件与默认规则通过 `FSMHelper` 创建：

```java
// 创建默认用户事件
FsmEvent submit = FSMHelper.newUserFsmEvent("SUBMIT");

// 创建带来源和描述的事件
FsmEvent accept = FSMHelper.newFsmEvent(
        "ACCEPT", FsmEventSource.System, "系统受理");

// 创建规则
FsmStateTransitionRule<EventType, StateType> rule =
        FSMHelper.newFsmStateTransitionRule(sourceState, event, targetState);
```

业务事件优先使用枚举实现 `FsmEvent`。如果需要使用字符串事件或状态，比较时会转换为名称文本处理；具体比较细节由 `FSMHelper`
统一处理。

## 查询可触发事件

给定一个状态，可以查询当前可触发的事件：

```java
List<EventType> events = FSMHelper.canFireEvents(fsmState);

// 或通过状态默认方法调用
List<EventType> events = fsmState.canFireEvents();

// 获取事件名称，便于展示
List<String> eventNames = FSMHelper.canFireEventNames(fsmState);
```

查询步骤如下：

1. 读取当前状态的 `transitionRules()`。
2. 保留源状态与当前状态匹配的规则。
3. 返回通过筛选的事件列表，保持规则声明顺序。

## 事件来源与界面呈现

通常只有 `FsmEventSource.User` 的事件应作为普通界面操作呈现。`System`、`Timer`、`External`、`Message`
等事件由后端流程、定时任务或外部输入触发，不应默认渲染为用户按钮。`Admin` 是否展示取决于当前用户的管理权限。
