package org.cowary.arttrackerback.dbCase.ranobe;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.cowary.arttrackerback.repo.ranobe.RanobeVolumeRepo;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RanobeVolumeCrud implements MediaCrud<RanobeVolume> {


    @Autowired
    RanobeCrud ranobeCrud;
    @Autowired
    RanobeVolumeRepo ranobeVolumeRepo;
    @Autowired
    UserService userService;

    public void save(RanobeVolume ranobeVolume, Ranobe ranobe) {
        ranobeVolume.setLastUpd(LocalDate.now());
        ranobe.setLastUpd(LocalDate.now());
        ranobeCrud.save(ranobe);
//        ranobeVolume.setRanobeId(ranobe.getId());
        ranobeVolume.setUsrId(userService.getIdCurrentUser());
        ranobeVolumeRepo.save(ranobeVolume);
    }

    public RanobeVolume save(RanobeVolume ranobeVolume) {
        ranobeVolume.setLastUpd(LocalDate.now());
        ranobeVolume.setUsrId(userService.getIdCurrentUser());
//        ranobeVolume.getRanobe().setLastUpd(LocalDate.now());
//        ranobeVolume.getRanobe().setUsrId(userService.getIdCurrentUser());
//        ranobeCrud.save(ranobeVolume.getRanobe());
        return ranobeVolumeRepo.save(ranobeVolume);
    }

//    public List<RanobeVolume> getAll(String status) {
//        List<RanobeVolume> ranobeVolumes;
//        long userId = userService.getIdCurrentUser();
//        if(status.equals("")) ranobeVolumes = ranobeVolumeCrud.findAllByUsrId(userId);
//        else ranobeVolumes = ranobeVolumeCrud.findAllByStatus(status);
//
//        fill(ranobeVolumes);
//        return ranobeVolumes;
//    }

    public RanobeVolume getById(long id) {
        return ranobeVolumeRepo.findById(id).orElseThrow();
    }

    public void delete(RanobeVolume ranobeVolume) {
        ranobeVolumeRepo.delete(ranobeVolume);
    }

    public void deleteById(long id) {
        ranobeVolumeRepo.deleteById(id);
    }

    @Override
    public List<RanobeVolume> getAll(long userId, String status) {
        List<RanobeVolume> ranobeVolumes;
        if(status.equals("")) ranobeVolumes = ranobeVolumeRepo.findAllByUsrId(userId);
        else ranobeVolumes = ranobeVolumeRepo.findAllByStatus(status);
        return ranobeVolumes;
    }

    public List<RanobeVolume> getAllByUserId(long userId) {
        return ranobeVolumeRepo.findAllByUsrId(userId);
    }
}
