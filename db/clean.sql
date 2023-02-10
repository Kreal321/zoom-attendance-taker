use attendance;

SET SQL_SAFE_UPDATES = 0;
delete from event;
delete from attendance;
delete from participant;
delete from meeting;

SET SQL_SAFE_UPDATES = 1;

ALTER TABLE attendance AUTO_INCREMENT = 1;
ALTER TABLE event AUTO_INCREMENT = 1;
ALTER TABLE participant AUTO_INCREMENT = 1;
ALTER TABLE meeting AUTO_INCREMENT = 1;