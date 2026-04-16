package com.jokenponline.api.dto.user;

import com.jokenponline.domain.enums.SearchingStatus;
import jakarta.validation.constraints.NotNull;

public record SearchingRequestDTO(
        @NotNull(message = "You need to return one status to execute this action!")
        SearchingStatus searchingStatus
) {
}
