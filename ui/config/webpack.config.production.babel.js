/**
 * Created by mak on 9/6/16.
 */
import config from './webpack.config.babel';
import webpack from 'webpack';

// config.devtool = '#source-map';
config.devtool = false;
config.plugins = [
  ...config.plugins,
  new webpack.DefinePlugin({
    'process.env': {
      'NODE_ENV': JSON.stringify('production')
    }
  })
];

export default config;
