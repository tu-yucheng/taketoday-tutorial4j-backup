module.exports = {
	preset: 'jest-preset-angular',
	setupTestFrameworkScriptFile: '<rootDir>/src/test/javascript/jest.ts',
	coverageDirectory: '<rootDir>/target/test-results/',
	globals: {
		'ts-jest': {
			tsConfigFile: 'tsconfig.json'
		},
		__TRANSFORM_HTML__: true
	},
	moduleNameMapper: {
		'app/(.*)': '<rootDir>/src/main/webapp/app/$1'
	},
	reporters: [
		'default',
		['jest-junit', {output: './target/test-results/jest/TESTS-results.xml'}]
	],
	testResultsProcessor: 'jest-sonar-reporter',
	transformIgnorePatterns: ['node_modules/(?!@angular/common/locales)'],
	testMatch: ['<rootDir>/src/test/javascript/spec/**/+(*.)+(spec.ts)'],
	rootDir: '../../../',
	testURL: "http://localhost/"
};
