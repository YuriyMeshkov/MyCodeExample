package com.jobc.j112kilo.data.mapper

import com.jobc.j112kilo.api.dto.ResponseDto
import com.jobc.j112kilo.data.model.Model


abstract class MapperFromResponse<From: ResponseDto, To: Model> {
    abstract fun map(from: From): To
}