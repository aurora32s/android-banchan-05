package com.seom.banchan.ui.model

enum class CellType {
    HEADER_CELL,
    MENU_LIST_CELL,
    MENU_CELL,
    MENU_LARGE_CELL,
    MENU_RECENTLY_CELL,
    FILTER_CELL,
    TOTAL_CELL,
    SORT_CELL,

    // 상세 화면
    IMAGE_SLIDER_CELL, // viewpager 이미지
    IMAGE_LIST_CELL, // 이미지 리스트
    MENU_DETAIL_INFO_CELL, // 메뉴 상세 정보
    DETAIL_COUNT_CELL, // 메뉴 개수 변경
    DETAIL_BOTTOM_BUTTON_CELL, // 메뉴 장바구니 추가 버튼 cell

    //장바구니 화면
    CART_CHECK_CELL,
    CART_MENU_CELL,
    CART_ORDER_CELL,
    CART_RECENT_CELL,

    //장바구니, 주문 내역, 주문 확인 화면 공통
    ORDER_INFO_CELL
}