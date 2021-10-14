CREATE TABLE rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) UNIQUE
    );

CREATE TABLE devices_executor (
        id INT NOT NULL PRIMARY KEY,
        name VARCHAR(40),
        type VARCHAR(10),
        status INT DEFAULT 0,
        minValue double DEFAULT 0,
        maxValue double DEFAULT 0,
        room_id INT DEFAULT -1);

CREATE TABLE devices_sensor(
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(40),
    type VARCHAR(10),
    room_id INT DEFAULT -1);

CREATE TABLE meanings(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT NOT NULL,
    meaning FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sensor_id) REFERENCES devices_sensor (id));


INSERT INTO rooms (name) VALUES
('room_1'),
('out');

INSERT INTO devices_sensor (id,name,type,room_id) VALUES
(00002,'sensor_1','termometr',1),
(00001,'sensor_2','termometr',2),
(00003,'sensor_3','hygrometer',2);

INSERT INTO devices_executor (id,name,type,room_id) VALUES
(00001, 'heater','heater',2),
(00002, 'searchlight','simple',2);


INSERT INTO meanings(sensor_id,meaning) VALUES
(00001,22),
(00002,0.5),
(00003,40);
