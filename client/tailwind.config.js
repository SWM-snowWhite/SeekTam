const px0_10 = { ...Array.from(Array(11)).map((_, i) => `${i}px`) }
const px0_100 = { ...Array.from(Array(101)).map((_, i) => `${i}px`) }
const px0_200 = { ...Array.from(Array(201)).map((_, i) => `${i}px`) }
const px0_500 = { ...Array.from(Array(501)).map((_, i) => `${i}px`) }

module.exports = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  theme: {
    colors: {
      main: '#0E6C57',
      sub: '#26957C',
      btn: '#2077FA',
      white: '#FFFFFF',
      good: '#1ABB6E',
      low_danger: '#FFA34F',
      high_danger: '#FF6E4F',
      unknown: '#96918C',
      info: '#F0F0F0',
      info_s: '#767676',
      modal: '#007BFF',
      black: '#000000',
      g100: '#F4F4F4',
      g200: '#E7E7E7',
      g300: '#B6B6B6',
      g400: '#9D9E9E',
      g500: '#858686',
      g600: '#6D6E6D',
      g700: '#545655',
      g800: '#3C3D3D',
      g900: '#232524',
      p100: '#E5FFF9',
      p200: '#D4FFF5',
      p300: '#C3FFF1',
      p400: '#B2FFEE',
      p500: '#A1FFEA',
      p600: '#75E6CD',
      p700: '#48BDA3',
      p800: '#26957C',
      p900: '#0E6C57',
      p1000: '#004334',
      red: '#EC3030',
    },
    extend: {
      borderWidth: px0_10,
      fontSize: px0_100,
      lineHeight: px0_100,
      minWidth: px0_200,
      minHeight: px0_200,
      spacing: px0_200,
      width: px0_500,
      height: px0_500,
    },
  },
  plugins: [],
}
