const path = require('path');
config.resolve.modules.push("../../processedResources/js/main");


if (config.devServer) {
    config.devServer.historyApiFallback = true
//    contentBase: path.join(__dirname, 'public'), // або встановіть відповідний каталог
//    publicPath: '/', // або встановіть відповідний шлях
    config.devServer.hot = true;
    config.devtool = 'eval-cheap-source-map';
} else {
    config.devtool = undefined;
}

// disable bundle size warning
config.performance = {
    assetFilter: function (assetFilename) {
      return !assetFilename.endsWith('.js');
    },
};

//это необходимо, если будет использоваться библиотека ETHERS v6+, но пока эта библиотека не работает, вылетает ошибка Uncaught ReferenceError: Buffer is not defined
config.resolve.fallback = {
  "zlib": false,
  "stream": false,
  "crypto": false,
  "http": false,
  "https": false,
  "net": false,
  "tls": false,
  "url": false,
  "bufferutil": false,
  "utf-8-validate": false
};