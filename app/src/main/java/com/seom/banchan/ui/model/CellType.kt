package com.seom.banchan.ui.model

enum class CellType {
    HEADER_CELL,
    MENU_LIST_CELL,
    MENU_CELL,
    MENU_LINEAR_CELL,
    MENU_GRID_CELL,
    FILTER_CELL,
    TOTAL_CELL,

    // 상세 화면
    IMAGE_SLIDER_CELL, // viewpager 이미지
    IMAGE_LIST_CELL, // 이미지 리스트
    MENU_DETAIL_INFO_CELL, // 메뉴 상세 정보
    DETAIL_COUNT_CELL, // 메뉴 개수 변경
    DETAIL_BOTTOM_BUTTON_CELL // 메뉴 장바구니 추가 버튼 cell
}