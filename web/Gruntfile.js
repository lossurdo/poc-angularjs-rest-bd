  module.exports = function( grunt ) {

      grunt.initConfig({

        connect: {
          server: {
            options: {
              port: 9000,
              base: 'public_html/',
              keepalive: true
            }
          }
        }

      });

      grunt.loadNpmTasks( 'grunt-contrib-connect' );

      grunt.registerTask( 'default', [ 'connect' ] );
  };