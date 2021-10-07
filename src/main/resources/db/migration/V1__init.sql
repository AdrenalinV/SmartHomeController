DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS devices_executor;
DROP TABLE IF EXISTS devices_sensor;
DROP TABLE IF EXISTS scripts_control;

CREATE TABLE devices_sensor(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40),
    type VARCHAR(10),
    meaning FLOAT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

INSERT INTO devices_sensor(name,type,meaning) VALUES
('Room_1','termometr',12),
('Room_2','termometr',10),
('Room_3','termometr',0.5);