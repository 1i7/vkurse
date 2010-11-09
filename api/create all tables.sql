CREATE TABLE teachers
(
  id serial NOT NULL,
  "name" character varying(255),
  degree character varying(255),
  CONSTRAINT teachers_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE examtypes
(
  id serial NOT NULL,
  "name" character varying(255),
  CONSTRAINT examtypes_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE groups
(
  id serial NOT NULL,
  "name" character varying(255),
  course character varying(255),
  CONSTRAINT groups_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE lectures
(
  id serial NOT NULL,
  "name" character varying(255),
  examtypeid integer NOT NULL,
  "comment" character varying(255),
  CONSTRAINT lectures_pkey PRIMARY KEY (id),
  CONSTRAINT lectures_examtypeid_fkey FOREIGN KEY (examtypeid)
      REFERENCES examtypes (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);


CREATE TABLE lecturetypes
(
  id serial NOT NULL,
  "name" character varying(255),
  CONSTRAINT lecturetypes_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE rooms
(
  id serial NOT NULL,
  "name" character varying(255),
  CONSTRAINT rooms_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE schedule
(
  id serial NOT NULL,
  groupid integer NOT NULL,
  "day" integer NOT NULL,
  starttime integer NOT NULL,
  length integer NOT NULL,
  lectureid integer NOT NULL,
  roomid integer NOT NULL,
  teacherid integer NOT NULL,
  lecturetypeid integer NOT NULL,
  "comment" character varying(255),
  CONSTRAINT schedule_pkey PRIMARY KEY (id),
  CONSTRAINT "schedule_groupID_fkey" FOREIGN KEY (groupid)
      REFERENCES groups (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedule_lectureID_fkey" FOREIGN KEY (lectureid)
      REFERENCES lectures (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedule_lectureTypeID_fkey" FOREIGN KEY (lecturetypeid)
      REFERENCES lecturetypes (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedule_roomID_fkey" FOREIGN KEY (roomid)
      REFERENCES rooms (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedule_teacherID_fkey" FOREIGN KEY (teacherid)
      REFERENCES teachers (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);


CREATE TABLE schedulechanges
(
  id serial NOT NULL,
  scheduleid integer NOT NULL,
  week integer NOT NULL,
  groupid integer NOT NULL,
  "day" integer NOT NULL,
  starttime integer NOT NULL,
  length integer NOT NULL,
  lectureid integer NOT NULL,
  roomid integer NOT NULL,
  teacherid integer NOT NULL,
  lecturetypeid integer NOT NULL,
  cancel integer NOT NULL,
  "comment" character varying(255),
  CONSTRAINT schedulechanges_pkey PRIMARY KEY (id),
  CONSTRAINT "schedulechanges_groupID_fkey" FOREIGN KEY (groupid)
      REFERENCES groups (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedulechanges_lectureID_fkey" FOREIGN KEY (lectureid)
      REFERENCES lectures (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedulechanges_lectureTypeID_fkey" FOREIGN KEY (lecturetypeid)
      REFERENCES lecturetypes (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedulechanges_roomID_fkey" FOREIGN KEY (roomid)
      REFERENCES rooms (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedulechanges_scheduleID_fkey" FOREIGN KEY (scheduleid)
      REFERENCES schedule (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT "schedulechanges_teacherID_fkey" FOREIGN KEY (teacherid)
      REFERENCES teachers (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);


