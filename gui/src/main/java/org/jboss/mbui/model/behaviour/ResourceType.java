package org.jboss.mbui.model.behaviour;

/**
 * @author Heiko Braun
 * @date 10/31/12
 */
public enum ResourceType {

    System, // produced by system, framework or other context of use
    Statement, // updates global dialog state
    Event, // produced by interaction units, consumed by behaviours
    Presentation // produced by transitions, consumed by interaction units
}
