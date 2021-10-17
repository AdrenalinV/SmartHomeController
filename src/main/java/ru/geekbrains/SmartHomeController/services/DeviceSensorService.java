package ru.geekbrains.SmartHomeController.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.SmartHomeController.dto.MeaningDTO;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;
import ru.geekbrains.SmartHomeController.repositories.DeviceSensorMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class DeviceSensorService {
    @Autowired
    private DeviceSensorMapper deviceSensorMapper;

    public List<DeviceSensor> getFreeSensor() {
        List<DeviceSensor> sensors = null;
        try {
            sensors = deviceSensorMapper.getSensorsByRoom_ID(-1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensors;
    }

    private Long addSensor(DeviceSensor ds) {
        try {
            return deviceSensorMapper.insert(ds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public void updateSensor(DeviceSensor ds) {
        try {
            deviceSensorMapper.update(ds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSensorValue(MeaningDTO meaningDTO) {
        DeviceSensor ds = null;
        try {
            if (!deviceSensorMapper.isExistsSensor(meaningDTO.getId())) {
                ds = this.toEntity(meaningDTO);
                addSensor(ds);
            } else {
                ds.setMeaning(meaningDTO.getMeaning());
            }
            deviceSensorMapper.updateMeaning(ds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public DeviceSensor getDeviceSensorByID(Long id) {
        try {
            return deviceSensorMapper.findById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public void deleteSensor(Long id) {
        try {
            if (deviceSensorMapper.isExistsSensor(id)) {
                DeviceSensor ds = deviceSensorMapper.findById(id);
                ds.setRoom_id(-1l);
                deviceSensorMapper.update(ds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DeviceSensor toEntity(MeaningDTO meaningDTO) {
        DeviceSensor ds = new DeviceSensor();
        ds.setId(meaningDTO.getId());
        ds.setType(meaningDTO.getType());
        ds.setMeaning(meaningDTO.getMeaning());
        ds.setRoom_id(-1L);
        return ds;
    }

}
