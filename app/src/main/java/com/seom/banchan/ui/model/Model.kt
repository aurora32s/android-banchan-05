package com.seom.banchan.ui.model

abstract class Model(
    open val id: String,
    open val type: CellType
)
enum class ModelId{
    HEADER,
    TOTAL,
    FILTER,
    SORT
}