package com.practice.client.core.project.base.domain.exceptions

import com.practice.network_entities.response_status.ErrorBody
import com.practice.network_entities.response_status.ResponseStatus

class BadRequestException(
    val errorBody: ErrorBody
) : StatusCodeException(ResponseStatus.BAD_REQUEST)


