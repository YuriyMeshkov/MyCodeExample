package com.jobc.j112kilo.data.mapper

import com.jobc.j112kilo.data.model.EntityModel
import com.jobc.j112kilo.data.model.Model

abstract  class MapperFromDatabase <From: EntityModel, To: Model> {
    abstract fun map(from: From): To
}