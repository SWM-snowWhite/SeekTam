const px0_10 = { ...Array.from(Array(11)).map((_, i) => `${i}px`) };
const px0_100 = { ...Array.from(Array(101)).map((_, i) => `${i}px`) };
const px0_200 = { ...Array.from(Array(201)).map((_, i) => `${i}px`) };
const px0_500 = { ...Array.from(Array(501)).map((_, i) => `${i}px`) };

module.exports = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  theme: {
    colors: {
      main: '#E95368',
      sub: '#FFA7B3',
      good: '#1ABB6E',
      low_danger: '#FFA34F',
      high_danger: '#FF6E4F',
      unknown: '#96918C',
      info: '#F0F0F0',
      info_s: '#767676',
      modal: "#007BFF"
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
