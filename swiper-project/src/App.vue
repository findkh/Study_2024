<template>
  <!-- Swiper 컴포넌트 -->
  <swiper
    :direction="'vertical'"
    :slidesPerView="1"
    :mousewheel="true"
    :pagination="paginationOptions"
    :modules="modules"
    :speed="1000"
    class="mySwiper"
  >
    <!-- 각 슬라이드 -->
    <swiper-slide>Slide 1</swiper-slide>
    <swiper-slide>Slide 2</swiper-slide>
    <swiper-slide>Slide 3</swiper-slide>
    <swiper-slide>Slide 4</swiper-slide>
    <swiper-slide>Slide 5</swiper-slide>
    <swiper-slide>Slide 6</swiper-slide>
  </swiper>
</template>

<script>
// Swiper Vue.js 컴포넌트 임포트
import { Swiper, SwiperSlide } from "swiper/vue";

// Swiper 스타일 임포트
import "swiper/css";
import "swiper/css/pagination";
import "./style.css";
import "./pagination.css";

// 필요한 모듈 임포트
import { Mousewheel, Pagination } from "swiper/modules";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    // 사용자 정의 renderBullet 함수 정의
    let title = [
      "슬라이드1",
      "슬라이드2",
      "슬라이드3",
      "슬라이드4",
      "슬라이드5",
      "슬라이드6",
    ];
    const renderCustomBullet = function (index, className) {
      console.log(className);
      return (
        '<div class="' +
        className +
        '"><span class="swiper-pagination-custom-bullet-span">' +
        title[index] +
        "</span></div>"
      );
    };

    // 페이징 옵션 구성
    const paginationOptions = {
      clickable: true,
      renderBullet: renderCustomBullet,
    };

    return {
      modules: [Mousewheel, Pagination],
      paginationOptions,
    };
  },
};
</script>

<style>
/* Swiper 페이징 스타일 */
.swiper-vertical > .swiper-pagination-bullets,
.swiper-pagination-vertical.swiper-pagination-bullets {
  width: 200px;
}

.swiper-vertical > .swiper-pagination-bullets,
.swiper-pagination-vertical.swiper-pagination-bullets {
  margin-left: 100px;
  right: auto;
  left: var(--swiper-pagination-left, 8px); /* 원하는 왼쪽 여백 설정 */
  top: 50%;
  transform: translate3d(0px, -50%, 0);
}

.swiper-vertical > .swiper-pagination-bullets .swiper-pagination-bullet,
.swiper-pagination-vertical.swiper-pagination-bullets
  .swiper-pagination-bullet {
  margin: var(--swiper-pagination-bullet-vertical-gap, 20px) 0;
  display: block;
}

/* 커스텀 페이징 스타일 */
.swiper-pagination-custom-bullet {
  display: flex;
  align-items: center;
}

.swiper-pagination-custom-bullet-span {
  margin-left: 110px; /* 수정된 부분: 여백 조절 */
  white-space: nowrap;
  font-size: 18px;
}

.swiper-vertical > .swiper-pagination-bullets .swiper-pagination-bullet,
.swiper-pagination-vertical.swiper-pagination-bullets
  .swiper-pagination-bullet {
  margin: var(--swiper-pagination-bullet-vertical-gap, 20px) 0;
  display: flex;
  align-items: center;
  justify-content: center; /* 중앙 정렬 추가 */
}
</style>
