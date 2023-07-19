module.exports = {
  rules: [
    {
      test: /\.css$/,
      use: ['style-loader', 'css-loader', 'postcss-loader'],
    },
    {
      test: /\.(png|jpe?g|gif)$/i,
      use: [{ loader: 'file-loader' }],
    },
  ],
}
