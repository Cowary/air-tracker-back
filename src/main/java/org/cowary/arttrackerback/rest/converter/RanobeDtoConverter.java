package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.cowary.arttrackerback.rest.dto.response.RanobeDtoRs;
import org.cowary.arttrackerback.rest.dto.response.RanobeVolumeDtoRs;

@UtilityClass
public class RanobeDtoConverter {

    public static RanobeDtoRs convert(Ranobe ranobe) {
        return RanobeDtoRs.builder()
                .id(ranobe.getId())
                .originalTitle(ranobe.getOriginalTitle())
                .title(ranobe.getTitle())
                .volumes(ranobe.getVolumes())
                .score(ranobe.getScore())
                .releaseDate(ranobe.getReleaseDate())
                .releaseYear(ranobe.getReleaseYear())
                .shikiId(ranobe.getShikiId())
                .lastUpd(ranobe.getLastUpd())
                .usrId(ranobe.getUsrId())
                .build();
    }

    public static Ranobe convert(RanobeDtoRs ranobeDtoRs) {
        return Ranobe.builder()
                .id(ranobeDtoRs.getId())
                .originalTitle(ranobeDtoRs.getOriginalTitle())
                .title(ranobeDtoRs.getTitle())
                .volumes(ranobeDtoRs.getVolumes())
                .score(ranobeDtoRs.getScore())
                .releaseDate(ranobeDtoRs.getReleaseDate())
                .releaseYear(ranobeDtoRs.getReleaseYear())
                .shikiId(ranobeDtoRs.getShikiId())
                .lastUpd(ranobeDtoRs.getLastUpd())
                .usrId(ranobeDtoRs.getUsrId())
                .build();
    }

    public static RanobeVolumeDtoRs convert(RanobeVolume ranobeVolume) {
        return RanobeVolumeDtoRs.builder()
                .id(ranobeVolume.getId())
                .title(ranobeVolume.getTitle())
                .number(ranobeVolume.getNumber())
                .status(ranobeVolume.getStatus())
                .score(ranobeVolume.getScore())
                .endDate(ranobeVolume.getEndDate())
                .ranobe(convert(ranobeVolume.getRanobe()))
                .usrId(ranobeVolume.getUsrId())
                .build();
    }

    public static RanobeVolume convert(RanobeVolumeDtoRs ranobeVolumeDtoRs) {
        return RanobeVolume.builder()
                .id(ranobeVolumeDtoRs.getId())
                .title(ranobeVolumeDtoRs.getTitle())
                .number(ranobeVolumeDtoRs.getNumber())
                .status(ranobeVolumeDtoRs.getStatus())
                .score(ranobeVolumeDtoRs.getScore())
                .endDate(ranobeVolumeDtoRs.getEndDate())
                .ranobe(convert(ranobeVolumeDtoRs.getRanobe()))
                .usrId(ranobeVolumeDtoRs.getUsrId())
                .build();
    }
}
