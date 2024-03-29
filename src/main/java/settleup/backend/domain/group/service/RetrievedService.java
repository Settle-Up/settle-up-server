package settleup.backend.domain.group.service;

import settleup.backend.domain.group.entity.dto.GroupInfoListDto;
import settleup.backend.domain.user.entity.dto.UserInfoDto;
import settleup.backend.global.exception.CustomException;

import java.util.List;

public interface RetrievedService {
 GroupInfoListDto getGroupInfoByUser(UserInfoDto userInfo)throws CustomException;
}
