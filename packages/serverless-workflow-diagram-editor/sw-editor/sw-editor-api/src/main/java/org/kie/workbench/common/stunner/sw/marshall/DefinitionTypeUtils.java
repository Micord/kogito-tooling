package org.kie.workbench.common.stunner.sw.marshall;

import elemental2.core.Reflect;
import org.kie.workbench.common.stunner.sw.definition.StartDefinition;
import org.kie.workbench.common.stunner.sw.definition.State;
import org.kie.workbench.common.stunner.sw.definition.StateTransition;
import org.kie.workbench.common.stunner.sw.definition.Workflow;

public class DefinitionTypeUtils {

    public static final String NEXT_STATE = "nextState";
    public static final String STATE_NAME = "stateName";
    public static final String TERMINATE = "terminate";

    public static boolean getEnd(Object end) {
        if (end instanceof Boolean) {
            return (boolean) end;
        } else if (end != null) {
            return true;
        }
        return false;
    }

    public static String getTransition(Object transition) {
        if (transition instanceof String) {
            return (String) transition;
        } else if (transition != null) {
            StateTransition stateTransition = (StateTransition) transition;
            return stateTransition.getNextState();
        }
        return null;
    }

    public static String getStart(Workflow workflow) {
        Object start = workflow.getStart();
        if (start instanceof String) {
            return (String) start;
        } else if (start != null) {
            Object stateName = ((StartDefinition) workflow.getStart()).getStateName();
            if (stateName != null) {
                return (String) stateName;
            } else {
                State[] states = workflow.getStates();
                return states[0].getName();
            }
        }

        return null;
    }

    public static Object getObjectProperty(Object object, String propertyName) {
        if (object != null) {
            return Reflect.get(object, propertyName);
        }
        return null;
    }

    public static void setObjectProperty(Object object, String propertyName, Object value) {
        if (object != null) {
            Reflect.set(object, propertyName, value);
        }
    }
}
