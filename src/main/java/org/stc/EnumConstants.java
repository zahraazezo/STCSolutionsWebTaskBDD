package org.stc;

public class EnumConstants {
    public enum Roles {
        MANAGER, CUSTOMER
    }

    public enum WaitConditions {
        EXIST,
        NOT_EXIST,
        VISIBLE,
        INVISIBLE,
        ENABLED,
        CLICKABLE,
        FRAME_AVAILABILITY_AND_SWITCH_TO_IT,
        SELECT_LIST_LOADED,
        PAGE_READY_STATE,
        PRESENCE_OF_ALL_ELEMENTS_LOCATED_BY,
        VISIBILITY_OF_ALL_ELEMENTS_LOCATED_BY,
        ELEMENT_TO_BE_SELECTED,
        ALERT_IS_PRESENT
    }

    public enum ManagerActions {
        ADD_CUSTOMER,
        VIEW_CUSTOMER_LIST,
        OPEN_ACCOUNT
    }

    public enum SortOrder {
        ASCENDING,
        DESCENDING
    }




}
