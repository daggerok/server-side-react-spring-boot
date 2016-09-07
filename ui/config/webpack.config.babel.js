/**
 * Created by mak on 9/6/16.
 */
import path from 'path';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import ExtractPlugin from 'extract-text-webpack-plugin';
import autoprefixer from 'autoprefixer';

const include   = [path.resolve(process.cwd(), './src')];
const assetsInclude   = [path.resolve(process.cwd(), './src/assets')];
const bsInclude = [path.resolve(process.cwd(), './node_modules/bootstrap/dist')];
const config = {
  entry: {
    app: './src/main.js'
  },
  output: {
    path: '../src/main/resources/static',
    filename: '/[name].js',
    sourceMapFilename: 'maps/[file].map'
  },
  module: {
    preLoaders: [{
      test: /\.js$/,
      exclude: /(node_modules|bower_components)/,
      loader: 'source-map-loader'
    }],
    loaders: [{
      test: /\.js$/,
      include,
      loader: 'babel',
      query: {
        presets: ['stage-0', 'es2015', 'react'],
        plugins: ['react-html-attrs', 'add-module-exports', 'transform-class-properties']
      }
    }, {
      test: /\.css/,
      include: bsInclude,
      // // fix: Module build failed: ReferenceError: window is not defined
      // loader: ExtractPlugin.extract('style!css')
      loader: ExtractPlugin.extract('style', 'css!postcss')
    }, {
      test: /\.less$/,
      include: assetsInclude,
      loader: ExtractPlugin.extract('style','css!postcss!less')
    }, {
      test: /\.styl/,
      include: assetsInclude,
      loader: ExtractPlugin.extract('style','css!postcss!stylus')
    }, {
      test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
      include: bsInclude,
      loader: 'file'
    }, {
      test: /\.(woff|woff2)$/,
      include: bsInclude,
      loader:'url?prefix=font/&limit=4096'
    }, {
      test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
      include: bsInclude,
      loader: 'url?limit=4096&mimetype=application/octet-stream'
    }, {
      test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
      include: bsInclude,
      loader: 'url?limit=4096&mimetype=image/svg+xml'
    }, {
      test: /\.hbs$/,
      include,
      loader: 'handlebars'
    }]
  },
  resolve: {
    extensions: ['', '.js'],
    modulesDirectories: ['node_modules']
  },
  plugins: [
    new ExtractPlugin('/[name].css', { allChunks: true }),
    new HtmlWebpackPlugin({ template: './src/assets/index.hbs' })
  ],
  postcss: [ autoprefixer({ browsers: ['last 2 versions'] }) ],
  devtool: '#cheap-module-inline-source-map',
  devServer: {
    historyApiFallback: true,
    inline:   true,
    progress: true
  }
};

export default config;
