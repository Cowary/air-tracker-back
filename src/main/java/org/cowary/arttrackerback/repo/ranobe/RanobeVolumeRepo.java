package org.cowary.arttrackerback.repo.ranobe;

import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobeVolumeRepo extends CrudRepository<RanobeVolume, Long> {

    List<RanobeVolume> findAll();
    List<RanobeVolume> findAllByUsrId(Long usrId);
    List<RanobeVolume> findAllByStatus(String status);
//    List<RanobeVolume> findAllByRanobeId(Long id);
}
