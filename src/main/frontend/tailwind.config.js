/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../resources/templates/**/*.{html,js}"],
  theme: {
    extend: {
        colors: {
                primary: "#92C7CF",
                secondary: "#E5E1DA",
                third: "#AAD7D9",
                fourth: "#FBF9F1",
                fifth: "#E5D9F2",
        },
    },
  },
  plugins: [],
}

