package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.BaseDataModel
import com.robert.banyai.wup.domain.model.BaseDomainModel

interface BaseConverter<DomainModel : BaseDomainModel, DataModel : BaseDataModel> {
    fun convertToDomain(dataModel: DataModel): DomainModel
}