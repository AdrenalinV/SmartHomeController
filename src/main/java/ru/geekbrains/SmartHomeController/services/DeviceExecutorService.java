package ru.geekbrains.SmartHomeController.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SmartHomeController.dto.StatusDTO;
import ru.geekbrains.SmartHomeController.entities.DeviceExecutor;
import ru.geekbrains.SmartHomeController.repositories.DeviceExecutorMapper;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DeviceExecutorService {
    @Autowired
    private DeviceExecutorMapper deviceExecutorMapper;

    public List<DeviceExecutor> getFreeExecutor() {
        List<DeviceExecutor> executors = null;
        try {
            executors = deviceExecutorMapper.getFreeExecutors();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executors;
    }

    private Long addExecutor(DeviceExecutor de) {
        try {
            return deviceExecutorMapper.insert(de);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public void updateExecutor(DeviceExecutor de) {
        try {
            deviceExecutorMapper.update(de);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateExecutorStatus(StatusDTO statusDTO) {
        DeviceExecutor de = null;
        try {
            if (!deviceExecutorMapper.isExistsExecutor(statusDTO.getId())) {
                de = this.toEntity(statusDTO);
                addExecutor(de);
            } else {
                de.setStatus(statusDTO.getStatus());
            }
            deviceExecutorMapper.update(de);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public DeviceExecutor getDeviceExecutorByID(Long id) {
        try {
            return deviceExecutorMapper.findById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public void deleteExecutor(Long id) {
        try {
            if (deviceExecutorMapper.isExistsExecutor(id)) {
                DeviceExecutor de = deviceExecutorMapper.findById(id);
                de.setRoom_id(-1l);
                deviceExecutorMapper.update(de);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DeviceExecutor toEntity(StatusDTO statusDTO) {
        DeviceExecutor de = new DeviceExecutor();
        de.setId(statusDTO.getId());
        de.setType(statusDTO.getType());
        de.setStatus(statusDTO.getStatus());
        de.setMinValue(0.00);
        de.setMaxValue(0.00);
        de.setRoom_id(-1L);
        return de;
    }
}
