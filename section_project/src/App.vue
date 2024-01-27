<template>
  <div id="app">
    <!-- Header 섹션 -->
    <header>
      <h1>This is the header</h1>
    </header>

    <!-- 메뉴 컨테이너 -->
    <div class="menu-container">
      <ul>
        <!-- 섹션 목록을 동적으로 생성 -->
        <li
          v-for="section in sections"
          :key="section"
          @click="scrollToSection(section)"
          :class="{ active: activeSection === section }"
        >
          {{ section }}
        </li>
      </ul>
    </div>

    <!-- 메인 섹션 (스크롤 이벤트 처리) -->
    <main @wheel.prevent="handleMouseWheel">
      <!-- 섹션 목록을 동적으로 생성 -->
      <section
        class="section"
        v-for="section in sections"
        :key="section"
        :id="section"
        :class="{ active: activeSection === section }"
      >
        <div>{{ section }}</div>
      </section>
    </main>

    <!-- Footer 섹션 -->
    <footer>
      <p>This is the footer</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      activeSection: null, // 현재 화면에서 가장 많이 보이는 섹션
      sections: ["section1", "section2", "section3", "section4"], // 섹션 목록
    };
  },
  mounted() {
    this.handleScroll(); // 페이지 로딩시 초기 섹션 상태 업데이트
    window.addEventListener("scroll", this.handleScroll); // 스크롤 이벤트 리스너 등록
  },
  beforeUnmount() {
    window.removeEventListener("scroll", this.handleScroll); // 컴포넌트 파괴 전에 이벤트 리스너 제거
  },
  methods: {
    // 섹션으로 스크롤 이동하는 메서드
    scrollToSection(sectionId) {
      const targetElement = document.getElementById(sectionId);
      if (targetElement) {
        window.scrollTo({
          top: targetElement.offsetTop - 50,
          behavior: "smooth",
        });
      }
    },

    // 스크롤 이벤트 처리 메서드
    handleScroll() {
      const scrollPosition = window.scrollY; // 현재 스크롤 위치
      const sections = this.sections;

      // 현재 화면에서 가장 많이 보이는 섹션 찾기
      let maxVisiblePercentage = 0;
      let visibleSection = null;

      for (const sectionId of sections) {
        const element = document.getElementById(sectionId);

        if (element) {
          const sectionTop = element.offsetTop;
          const sectionBottom = sectionTop + element.offsetHeight;

          const visibleTop = Math.max(sectionTop, scrollPosition);
          const visibleBottom = Math.min(
            sectionBottom,
            scrollPosition + window.innerHeight
          );
          const visibleHeight = visibleBottom - visibleTop;

          const visiblePercentage =
            (visibleHeight / element.offsetHeight) * 100;

          if (visiblePercentage > maxVisiblePercentage) {
            maxVisiblePercentage = visiblePercentage;
            visibleSection = sectionId;
          }
        }
      }

      this.activeSection = visibleSection; // 현재 보이는 섹션 업데이트
    },

    // 마우스 휠 이벤트 처리 메서드
    handleMouseWheel(event) {
      const delta = event.deltaY;

      if (delta > 0) {
        // 아래로 휠을 돌릴 때
        this.scrollNextSection();
      } else if (delta < 0) {
        // 위로 휠을 돌릴 때
        this.scrollPrevSection();
      }
    },

    // 다음 섹션으로 스크롤하는 메서드
    scrollNextSection() {
      const currentIndex = this.sections.indexOf(this.activeSection);
      if (currentIndex < this.sections.length - 1) {
        this.scrollToSection(this.sections[currentIndex + 1]);
      }
    },

    // 이전 섹션으로 스크롤하는 메서드
    scrollPrevSection() {
      const currentIndex = this.sections.indexOf(this.activeSection);
      if (currentIndex > 0) {
        this.scrollToSection(this.sections[currentIndex - 1]);
      }
    },
  },
};
</script>
<style>
#app {
  text-align: center;
}
body {
  margin: 0;
}
header {
  background-color: #41b883;
  color: white;
  padding: 10px;
}

footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #41b883;
  color: white;
  padding: 10px;
}

.menu-container {
  position: fixed;
  top: 50%;
  left: 100px;
  width: 120px;
  transform: translateY(-50%);
  z-index: 100;
  font-size: 20px;
}
li {
  margin-bottom: 10px;
}
.menu-container ul li {
  cursor: pointer;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  text-decoration: none;
  color: #333;
  display: block;
  padding: 5px;
  border-radius: 5px;
  margin: 10px 0;
  cursor: pointer;
  transition: background-color 0.3s;
}

li:hover {
  background-color: #ddd;
}

li.active {
  background-color: #41b883;
  color: white;
}

.section {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

#section1 {
  background-color: #ffcccb;
}

#section2 {
  background-color: #add8e6;
}

#section3 {
  background-color: #90ee90;
}

#section4 {
  background-color: #ffd700;
}
</style>
