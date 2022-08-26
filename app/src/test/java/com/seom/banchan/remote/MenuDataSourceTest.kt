package com.seom.banchan.remote

import com.seom.banchan.data.api.MenuApiService
import com.seom.banchan.data.api.SortCriteria
import com.seom.banchan.data.api.response.best.Category
import com.seom.banchan.data.api.response.best.Menu
import com.seom.banchan.data.api.response.best.toModel
import com.seom.banchan.data.source.MenuDataSource
import com.seom.banchan.data.source.remote.MenuDataSourceImpl
import com.seom.banchan.domain.model.detail.DetailMenuModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MenuDataSourceTest {

    private lateinit var service: MenuApiService
    private lateinit var menuDataSource: MenuDataSource

    @Before
    fun setup() {
        service = FakeMenuApiService()
        menuDataSource = MenuDataSourceImpl(
            menuApiService = service
        )
    }

    @Test
    fun `기획전_메뉴를_조회할_수_있다`() = runTest {
        val expected = Result.success(
            listOf(
                Category(
                    categoryId = "17011000",
                    name = "풍성한 고기반찬",
                    items = listOf(
                        Menu(
                            alt = "오리 주물럭_반조리",
                            detailHash = "HBDEF",
                            image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                            deliveryType = listOf(
                                "새벽배송",
                                "전국택배"
                            ),
                            title = "오리 주물럭_반조리",
                            description = "감칠맛 나는 매콤한 양념",
                            nPrice = "15,800원",
                            sPrice = "12,640원",
                            badge = listOf(
                                "런칭특가"
                            )
                        ),
                        Menu(
                            alt = "소갈비찜",
                            detailHash = "HF778",
                            image = "http://public.codesquad.kr/jk/storeapp/data/main/349_ZIP_P_0024_T.jpg",
                            deliveryType = listOf(
                                "새벽배송",
                                "전국택배"
                            ),
                            title = "소갈비찜",
                            description = "촉촉하게 벤 양념이 일품",
                            nPrice = "28,900원",
                            sPrice = "26,010원",
                            badge = listOf(
                                "이벤트특가"
                            )
                        )
                    )
                ).toModel()
            )
        )
        val actual = menuDataSource.getBestMenus()
        assertEquals(
            expected,actual
        )
    }

    @Test
    fun `메인_메뉴를_조회할_수_있다`() = runTest {
        val expected = Result.success(
            listOf(
                Menu(
                    alt = "오리 주물럭_반조리",
                    detailHash = "HBDEF",
                    image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "오리 주물럭_반조리",
                    description = "감칠맛 나는 매콤한 양념",
                    nPrice = "15,800원",
                    sPrice = "12,640원",
                    badge = listOf(
                        "런칭특가"
                    )
                ).toModel(),
                Menu(
                    alt = "잡채",
                    detailHash = "HDF73",
                    image = "http://public.codesquad.kr/jk/storeapp/data/main/310_ZIP_P_0012_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "잡채",
                    description = "탱글한 면발과 맛깔진 고명이 가득",
                    nPrice = "12,900원",
                    sPrice = "11,610원",
                    badge = listOf(
                        "이벤트특가"
                    )
                ).toModel()
            )
        )
        val actual = menuDataSource.getMainMenus(SortCriteria.BASE)
        assertEquals(
            expected,actual
        )
    }

    @Test
    fun `국물_메뉴를_조회할_수_있다`() = runTest {
        val expected = Result.success(
            listOf(
                Menu(
                    alt = "한돈 돼지 김치찌개",
                    detailHash = "H72C3",
                    image = "http://public.codesquad.kr/jk/storeapp/data/soup/28_ZIP_P_1003_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "한돈 돼지 김치찌개",
                    description = "김치찌개에는 역시 돼지고기",
                    nPrice = "9,300원",
                    sPrice = "8,370원",
                    badge = listOf(
                        "이벤트특가"
                    )
                ).toModel(),
                Menu(
                    alt = "된장찌개",
                    detailHash = "HA6EE",
                    image = "http://public.codesquad.kr/jk/storeapp/data/soup/33_ZIP_P_1004_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "된장찌개",
                    description = "특별하지 않아서 더 좋은 우리맛",
                    nPrice = "8,800원",
                    sPrice = "7,920원",
                    badge = listOf(
                        "이벤트특가"
                    )
                ).toModel()
            )
        )
        val actual = menuDataSource.getSoupMenus(SortCriteria.BASE)
        assertEquals(
            expected,actual
        )
    }

    @Test
    fun `반찬_메뉴를_조회할_수_있다`() = runTest {
        val expected = Result.success(
            listOf(
                Menu(
                    alt = "새콤달콤 오징어무침",
                    detailHash = "HBBCC",
                    image = "http://public.codesquad.kr/jk/storeapp/data/side/48_ZIP_P_5008_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "새콤달콤 오징어무침",
                    description = "국내산 오징어를 새콤달콤하게",
                    nPrice = "7,500원",
                    sPrice = "6,000원",
                    badge = listOf(
                        "런칭특가"
                    )
                ).toModel(),
                Menu(
                    alt = "호두 멸치볶음",
                    detailHash = "H1939",
                    image = "http://public.codesquad.kr/jk/storeapp/data/side/17_ZIP_P_6014_T.jpg",
                    deliveryType = listOf(
                        "새벽배송",
                        "전국택배"
                    ),
                    title = "호두 멸치볶음",
                    description = "잔멸치와 호두가 만나 짭쪼름하지만 고소하게!",
                    nPrice = "5,800원",
                    sPrice = "5,220원",
                    badge = listOf(
                        "이벤트특가"
                    )
                ).toModel()
            )
        )
        val actual = menuDataSource.getSideMenus(SortCriteria.BASE)
        assertEquals(
            expected,actual
        )
    }

    @Test
    fun `메뉴_상세_정보를_조회할_수_있다`() = runTest {
        val expected = Result.success(
            DetailMenuModel(
                id = "HBDEF",
                thumbnail = listOf(
                    "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                    "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_S.jpg"
                ),
                description = "오리 주물럭_반조리",
                point = 126,
                deliveryInfo = "서울 경기 새벽 배송, 전국 택배 배송",
                deliveryFee = "2,500원 (40,000원 이상 구매 시 무료)",
                salePrice = 12640,
                normalPrice = 15800,
                detailImage = listOf(
                    "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D1.jpg",
                    "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D2.jpg",
                    "http://public.codesquad.kr/jk/storeapp/data/pakage_regular.jpg"
                )
            )
        )
        val actual = menuDataSource.getMenuDetail("HBDEF")
        assertEquals(
            expected,actual
        )
    }
}