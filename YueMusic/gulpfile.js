var gulp = require('gulp'),
	cssmin = require('gulp-minify-css'),
	imagemin = require('gulp-imagemin'),
	autoprefixer = require('gulp-autoprefixer'),
	uglify = require('gulp-uglify');

gulp.task('testAutoFx',function(){
	gulp.src('WebRoot/css/*.css')
	.pipe(autoprefixer({
		browsers:['last 2 versions'],
		cascade:true,
		remove:true
	}))
	.pipe(gulp.dest('WebRoot/dist/css'));
});

gulp.task('testCssmin',['testAutoFx'],function(){
	gulp.src('WebRoot/css/*.css')
	.pipe(cssmin({
		advanced:true,
		compatibility:'ie7',
		keepBreaks:false,
		keepSpecialComments:'*'
	}))
	.pipe(gulp.dest('WebRoot/dist/css'));
});

gulp.task('testImagemin',function(){
	gulp.src('WebRoot/b img/*.jpg')
	.pipe(imagemin())
	.pipe(gulp.dest('WebRoot/dist/img/b img'));
});

gulp.task('testJsmin',function(){
	gulp.src('WebRoot/js/*.js')
	.pipe(uglify())
	.pipe(gulp.dest('WebRoot/dist/js'));
});