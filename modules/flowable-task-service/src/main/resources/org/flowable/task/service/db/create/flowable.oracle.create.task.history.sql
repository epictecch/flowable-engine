create table ACT_HI_TASKINST (
    ID_ NVARCHAR2(64) not null,
    REV_ INTEGER default 1,
    PROC_DEF_ID_ NVARCHAR2(64),
    TASK_DEF_ID_ NVARCHAR2(64),
    TASK_DEF_KEY_ NVARCHAR2(255),
    PROC_INST_ID_ NVARCHAR2(64),
    EXECUTION_ID_ NVARCHAR2(64),
    SCOPE_ID_ NVARCHAR2(255),
    SUB_SCOPE_ID_ NVARCHAR2(255),
    SCOPE_TYPE_ NVARCHAR2(255),
    SCOPE_DEFINITION_ID_ NVARCHAR2(255),
    PROPAGATED_STAGE_INST_ID_ NVARCHAR2(255),
    PARENT_TASK_ID_ NVARCHAR2(64),
    NAME_ NVARCHAR2(255),
    DESCRIPTION_ NVARCHAR2(2000),
    OWNER_ NVARCHAR2(255),
    ASSIGNEE_ NVARCHAR2(255),
    START_TIME_ TIMESTAMP(6) not null,
    CLAIM_TIME_ TIMESTAMP(6),
    END_TIME_ TIMESTAMP(6),
    DURATION_ NUMBER(19,0),
    DELETE_REASON_ NVARCHAR2(2000),
    PRIORITY_ INTEGER,
    DUE_DATE_ TIMESTAMP(6),
    FORM_KEY_ NVARCHAR2(255),
    CATEGORY_ NVARCHAR2(255),
    TENANT_ID_ NVARCHAR2(255) default '',
    LAST_UPDATED_TIME_ TIMESTAMP(6),
    primary key (ID_)
);

create table ACT_HI_TSK_LOG (
    ID_ NUMBER(19),
    TYPE_ NVARCHAR2(64),
    TASK_ID_ NVARCHAR2(64) not null,
    TIME_STAMP_ TIMESTAMP(6) not null,
    USER_ID_ NVARCHAR2(255),
    DATA_ NVARCHAR2(2000),
    EXECUTION_ID_ NVARCHAR2(64),
    PROC_INST_ID_ NVARCHAR2(64),
    PROC_DEF_ID_ NVARCHAR2(64),
    SCOPE_ID_ NVARCHAR2(255),
    SCOPE_DEFINITION_ID_ NVARCHAR2(255),
    SUB_SCOPE_ID_ NVARCHAR2(255),
    SCOPE_TYPE_ NVARCHAR2(255),
    TENANT_ID_ NVARCHAR2(255) default '',
    primary key (ID_)
);

create sequence act_hi_task_evt_log_seq start with 1 increment by 1;

create index ACT_IDX_HI_TASK_SCOPE on ACT_HI_TASKINST(SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SUB_SCOPE on ACT_HI_TASKINST(SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SCOPE_DEF on ACT_HI_TASKINST(SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_HI_TASKLOG_TSK on ACT_HI_TSK_LOG(TASK_ID_);
create index ACT_IDX_HI_TASKLOG_PRC on ACT_HI_TSK_LOG(PROC_INST_ID_);
create index ACT_IDX_HI_TASKLOG_SCOPE on ACT_HI_TSK_LOG(SCOPE_ID_, SCOPE_TYPE_);
