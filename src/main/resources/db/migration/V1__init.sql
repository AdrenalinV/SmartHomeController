CREATE TABLE rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) UNIQUE
    );

CREATE TABLE devices_executor (
        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(40),
        type VARCHAR(10),
        status INT DEFAULT 0,
        minValue double DEFAULT 0,
        maxValue double DEFAULT 0,
        room_id INT NOT NULL,
        FOREIGN KEY (room_id) REFERENCES rooms (id));

CREATE TABLE devices_sensor(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40),
    type VARCHAR(10),
    room_id INT NOT NULL,
FOREIGN KEY (room_id) REFERENCES rooms (id));

CREATE TABLE meanings(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT NOT NULL,
    meaning FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sensor_id) REFERENCES devices_sensor (id));


INSERT INTO rooms (name) VALUES
('room_1'),
('out');

INSERT INTO devices_sensor (name,type,room_id) VALUES
('sensor_1','termometr',1),
('sensor_2','termometr',2),
('sensor_3','hygrometer',2);

INSERT INTO devices_executor (name,type,room_id) VALUES
('heater','heater',2),
('searchlight','simple',2);


INSERT INTO meanings(sensor_id,meaning) VALUES
(1,22),
(2,0.5),
(3,40);
