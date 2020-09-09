[![Carina - Configuration](https://raw.githubusercontent.com/qaprosoft/carina/master/docs/img/video.png)](https://youtu.be/MMviWxCS9x4)

### Configuration files
There are multiple properties files located in src/main/resources:

*  **api.properties** - API test endpoints reference
*  **config.properties** - global test configuration
*  **database.properties** - database connection properties
*  **email.properties** - emailable reports config
*  **testdata.properties** - test user credentials 

All the properties may be retrieved in a test using R class:
```
R.API.get("GetUserMethods")
R.CONFIG.get("browser")
R.DATABASE.get("db.url")
R.EMAIL.get("title")
R.TESTDATA.get("user.email")
```
The default config properties can be obtained by
```
Configuration.get(Parameter.BROWSER)
```

All the project configuration properties are located in a **_config.properties** file. In the table below we are providing a description of most of the parameters:
<table>
	<tr>
		<th>Attribute</th>
		<th>Meaning</th>
		<th>Example</th>
	</tr>
	<tr>
		<td>url</td>
		<td>Base application URL</td>
		<td>http://qaprosoft.com</td>
	</tr>
	<tr>
		<td>browser</td>
		<td>Browser for testing</td>
		<td>chrome / firefox / safari / iexplore</td>
	</tr>
	<tr>
		<td>selenium_host</td>
		<td>Selenium server host</td>
		<td>http://localhost:4444/wd/hub</td>
	</tr>
	<tr>
		<td>app_version</td>
		<td>Application version/build number for reporting</td>
		<td>1.2.5</td>
	</tr>
	<tr>
		<td>locale</td>
		<td>Locale for using L10N feature. Enabled when enable_l10n=true</td>
		<td>en_GB,de_DE,fr_FR</td>
	</tr>
	<tr>
		<td>retry_interval</td>
		<td>Timeout interval between calling HTML DOM for the element.<br><b>Note:</b> in ms. For mobile automation specify a number from 500-1500 range</td>
		<td>Integer</td>
	</tr>
	<tr>
		<td>auto_screenshot</td>
		<td>Global switch for taking screenshots. When disabled, only failures will be captured</td>
		<td>Boolean</td>
	</tr>
	<tr>
		<td>report_url</td>
		<td>Direct HTTP link to Jenkins workspace report folder. Automatically specified by CI</td>
		<td>http://localhost:8888/job /my_project/1/eTAF_Report</td>
	</tr>
	<tr>
		<td>max_screen_history</td>
		<td>Max number of reports in history</td>
		<td>Integer</td>
	</tr>
	<tr>
		<td>jira_url</td>
		<td>JIRA base URL for direct links with bugs description</td>
		<td>https://jira.carina.com/browse/</td>
	</tr>
	<tr>
		<td>email_list</td>
		<td>Comma-separated list of emails for reports</td>
		<td>u1@gmail.com,u2@gmail.com</td>
	</tr>
	<tr>
		<td>env</td>
		<td>Environment specific configuration. More about this [feature](#environment-specific-configuration)</td>
		<td>STAG, PROD, DEMO</td>
	</tr>
	<tr>
		<td>env_arg_resolver</td>
		<td>This parameter is optional, if it isn't set, the default value will be used. In most cases, <b> the default value is enough</b></td>
		<td>java class </td>
	</tr>
		<tr>
		<td>browser_version</td>
		<td>Browser version or an empty string if unknown for Selenium Grid</td>
		<td>"8.0", "52.1"</td>
	</tr>
		<tr>
		<td>browser_language</td>
		<td>Browser language or nothing to use the English version by default. <br><b>Note:</b> Only applicable for Chrome and Firefox!</td>
		<td>"es", "fr"</td>
	</tr>
	<tr>
		<td>driver_event_listeners</td>
		<td>Comma-separated list of listeners. Listeners provide more logs from WebDriver and have to be the instances of WebDriverEventListener</td>
		<td>com.someCompane.core.EventListener</td>
	</tr>
		<tr>
		<td>max_driver_count</td>
		<td>Max number of drivers per thread</td>
		<td>Integer</td>
	</tr>
		<tr>
		<td>custom_capabilities</td>
		<td>Name of a properties file with custom capabilities (key-value)</td>
		<td>custom.properties</td>
	</tr>
		<tr>
		<td>proxy_host</td>
		<td>Hostname of the server</td>
		<td>host.example.com</td>
	</tr>
		<tr>
		<td>proxy_port</td>
		<td>Port number</td>
		<td>80</td>
	</tr>
		<tr>
		<td>proxy_protocols</td>
		<td>Comma-separated list of internet protocols used to carry the connection information from the source requesting the connection to the destination for which the connection was requested.</td>
		<td>http, https, ftp, socks</td>
	</tr>
		<tr>
		<td>browsermob_proxy</td>
		<td>Boolean parameter which enables or disables the automatic BrowserMob proxy launch</td>
		<td>true, false</td>
	</tr>
		<tr>
		<td>browsermob_port</td>
		<td>Port number for BrowserMob proxy (if nothing or 0 specified, then any free port will be reused)</td>
		<td>Integer</td>
	</tr>
		<tr>
		<td>proxy_set_to_system</td>
		<td>Boolean parameter which enables or disables the setup of a proxy</td>
		<td>true, false</td>
	</tr>
		<tr>
		<td>no_proxy</td>
		<td>Excluded hostname(s) for communication via proxy. Available only when proxy_host and proxy_port are declared!</td>
		<td>localhost.example.com</td>
	</tr>
		<tr>
		<td>failure_email_list</td>
		<td>Comma-separated list of emails for failure reports</td>
		<td>u1@mail.com,u2@mail.com</td>
	</tr>
		<tr>
		<td>track_known_issues</td>
		<td>Boolean parameter. If it is true and some Jira tickets are associated with the test, in case of failure Jira info will be added to the report</td>
		<td>true,false</td>
	</tr>
	<tr>
		<td>explicit_timeout</td>
		<td>Timeout is seconds to wait for a certain condition to occur before proceeding further in the code</td>
		<td>Integer</td>
	</tr>
	<tr>
		<td>auto_download</td>
		<td>The enabled parameter prevents downloading a dialog and downloading a file automatically. The feature is currently available for Chrome and FireFox</td>
		<td>false, true</td>
	</tr>
	<tr>
		<td>auto_download_apps</td>
		<td>MIME types / Internet Media Types. The parameter is needed only to configure auto downloading for FireFox</td>
		<td>application/pdf, list of [values](https://freeformatter.com/mime-types-list.html)</td>
	</tr>
	<tr>
		<td>auto_download_folder</td>
		<td>Path to auto download folder for Chrome and Firefox browsers. If nothing specified custom_artifacts_folder or default artifacts folder is used</td>
		<td>String</td>
	</tr>
	<tr>
		<td>project_report_directory</td>
		<td>Path to a folder where the reports will be saved</td>
		<td>./reports/qa</td>
	</tr>
	<tr>
		<td>big_screen_width</td>
		<td>Screenshots will be resized according to this width if their own width is bigger</td>
		<td>500, 1200, Integer</td>
	</tr>
	<tr>
		<td>big_screen_height</td>
		<td>Screenshots will be resized according to this height if their own height is bigger</td>
		<td>500, 1200, Integer</td>
	<tr>
		<td>init_retry_count</td>
		<td>Number of attempts to create a driver. The default value 0 means that there will be only 1 attempt</td>
		<td>Integer</td>
	</tr>
	<tr>
		<td>init_retry_interval</td>
		<td>Interval in seconds between the attempts to create a driver</td>
		<td>Integer</td>
	</tr>
	<tr>
		<td>retry_count</td>
		<td>Number of test-retryings in case of failure. The default value 0 means that a test will be performed only once</td>
		<td>Integer</td>
	</tr>
		<tr>
		<td>enable_l10n</td>
		<td>Enables L10N feature</td>
		<td>false, true</td>
	</tr>
			<tr>
		<td>l10n_encoding</td>
		<td>Charset for l10n feature</td>
		<td>ISO-8859-5, ISO-8859-6, UTF-8</td>
	</tr>
		<tr>
		<td>thread_count</td>
		<td>Default number of threads to use when running tests in parallel. Set thread_count=custom to disable any updates on carina side.</td>
		<td>Integer</td>
	</tr>
		<tr>
		<td>data_provider_thread_count</td>
		<td>Default number of threads to use for data providers when running tests in parallel.</td>
		<td>Integer</td>
	</tr>
		<tr>
		<td>core_log_level</td>
		<td>Level for Carina logging</td>
		<td>ALL, DEBUG, ERROR, WARN, FATAL, INFO, OFF, TRACE</td>
	</tr>
		<tr>
		<td>core_log_packages</td>
		<td>Comma-separated list of core packages where you want to redefine the log level</td>
		<td>com.qaprosoft.carina.core, ZafiraConfigurator etc</td>
	</tr>
		<tr>
		<td>log_all_json</td>
		<td>API response will be logged in JSON format</td>
		<td>true, false</td>
	</tr>
		<tr>
		<td>date_format</td>
		<td>Date format for DateUtils.class</td>
		<td>HH:mm:ss dd/MM/yyyy, HH:mm MM/dd/yyyy</td>
	</tr>
		<tr>
		<td>time_format</td>
		<td>Date format for DateUtils.class</td>
		<td>HH:mm:ss.SSS, HH:mm a zzz</td>
	</tr>
		<tr>
		<td>crypto_key_path</td>
		<td>Path to a file with a crypto key</td>
		<td>./src/main/resources/crypto.key</td>
	</tr>
		<tr>
		<td>suite_name</td>
		<td>Suite name for the report and TestRail. If this parameter is NULL, will be taken from TestNG xml (the parameter suite name) or _email.properties (the title)</td>
		<td>Advanced Acceptance</td>
	</tr>
	<tr>
		<td>jira_url</td>
		<td>URL to Jira</td>
		<td>https://yourclass.atlassian.net</td>
	</tr>
	<tr>
		<td>testrail_milestone</td>
		<td>Milestone to set on TestRail for run</td>
		<td>some-milestone</td>
	</tr>
		<tr>
		<td>testrail_assignee</td>
		<td>User assigned for the suit</td>
		<td>asignee_user@yuorcompany.com</td>
	</tr>
		<tr>
		<td>access_key_id</td>
		<td>Access key id for Amazon S3. More info [here](#https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html#access-keys-and-secret-access-keys)</td>
		<td>gkhcvdgvceUYF67897hbjsbdc</td>
	</tr>
		<tr>
		<td>secret_key</td>
		<td>Secret key for Amazon S3. More info [here](#https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html#access-keys-and-secret-access-keys)</td>
		<td>gkhcvdgvceUYF67897hbjsbdc</td>
	</tr>
		<tr>
		<td>s3_local_storage</td>
		<td>Directory for downloading artefacts</td>
		<td>./s3</td>
	</tr>
		<tr>
		<td>appcenter_token</td>
		<td>Token for authentication in Hockey App</td>
		<td>gkhcvdgvceUYF67897hbjsbdc</td>
	</tr>
		<tr>
		<td>appcenter_local_storage</td>
		<td>Directory for AppCenter artifacts</td>
		<td>./appcenter</td>
	</tr>
		<tr>
		<td>add_new_localization</td>
		<td>Should be set to 'true' if you want to create new localization files for the required Locale. Otherwise, there will be just the localization checking</td>
		<td>false, true</td>
	</tr>
		<tr>
		<td>add_new_localization_encoding</td>
		<td>Encoding for a new localization</td>
		<td>utf-16, utf-32</td>
	</tr>
		<tr>
		<td>add_new_localization_path</td>
		<td>Path where created localization properties should be saved. If null, they will be added to an artifactory folder in the report</td>
		<td>utf-16, utf-32</td>
	</tr>
		<tr>
		<td>add_new_localization_property_name</td>
		<td>Path where created localization properties should be saved. If null, they will be added to an artifactory folder in the report</td>
		<td>Basic template for property name.</td>
	</tr>
		<tr>
		<td>tls_keysecure_location</td>
		<td>Path to a directory with tls secure keys</td>
		<td>./tls/keysecure</td>
	</tr>
		<tr>
		<td>health_check_class</td>
		<td>Class to execute health checks</td>
		<td>Custom class</td>
	</tr>
		<tr>
		<td>health_check_methods</td>
		<td>Comma-separated list of methods of health_check_class to execute preliminarily</td>
		<td>doThis, doThat</td>
	</tr>
	<tr>
		<td>test_run_rules</td>
		<td>Executing rules logic: test_run_rules={RULE_NAME_ENUM}=>{RULE_VALUE1}&&{RULE_VALUE2};;...</td>
		<td>test_run_rules=PRIORITY=>P1&amp;&amp;P2;;OWNER=>owner;;TAGS=>tag1=temp&amp;&amp;feature=reg</td>
	</tr>
	<tr>
		<td>element_loading_strategy</td>
		<td>Determines how carina detects appearing of web elements on page: by presence in DOM model or by visibility or by any of these conditions</td>
		<td>BY_PRESENCE, BY_VISIBILITY, BY_PRESENCE_OR_VISIBILITY</td>
	</tr>
	<tr>
		<td>page_opening_strategy</td>
		<td>Determines how carina detects whether expected page is opened: by expected url pattern, by marker element loading state or by both these conditions</td>
		<td>BY_ELEMENT, BY_URL, BY_URL_AND_ELEMENT</td>
	</tr>
	<tr>
		<td>forcibly_disable_driver_quit</td>
		<td>If enabled turns off webdriver shutdown after test finishing by any reason. Default value is 'false'</td>
		<td>false, true</td>
	</tr>
</table>
Most of the properties may be read in the following way:
```
Configuration.get(Parameter.URL) // returns string value
Configuration.getBoolean(Parameter.AUTO_SCREENSHOT) // returns boolean value
Configuration.getInt(Parameter.BIG_SCREEN_WIDTH) //return int value
Configuration.getDouble(Parameter.BROWSER_VERSION) // returns double value
```

### Environment specific configuration
In some cases, it is required to support multiple environments for testing. Let's assume we have STAG and PROD environments which have different application URLs. In this case, we need to specify the following properties in _config.properties:
```
env=PROD
STAG.url=http://stag-app-server.com
PROD.url=http://prod-app-server.com
```

And get an env-specific argument in the test in the following way:
```
Configuration.getEnvArg("url")
```
As a result, you switch between the environments just changing the env argument in the _config.properties file.

### [Zafira](https://github.com/qaprosoft/zafira) configuration
[**zafira.properties**](https://github.com/qaprosoft/carina-demo/blob/master/src/main/resources/zafira.properties) are used for Zafira QA reporting integration, here you should specify some values for a proper integration:<table>
	<tr>
		<th>Attribute</th>
		<th>Meaning</th>
		<th>Example</th>
	</tr>
	<tr>
		<td>zafira_enabled</td>
		<td>Root switch</td>
		<td>true/false</td>
	</tr>
	<tr>
		<td>zafira_service_url</td>
		<td>Webservice URL</td>
		<td>http://localhost:8080/zafira-ws</td>
	</tr>
	<tr>
		<td>zafira_project</td>
		<td>Project name (created in Zafira)</td>
		<td>empty or any created</td>
	</tr>
	<tr>
		<td>zafira_rerun_failures</td>
		<td>Reruns only failures</td>
		<td>true/false</td>
	</tr>
	<tr>
		<td>zafira_report_emails</td>
		<td>List of emails for report</td>
		<td>user1@qps.com,user2@qps.com</td>
	</tr>
	<tr>
		<td>zafira_configurator</td>
		<td>Configurator class (used by default)</td>
		<td>com.qaprosoft.carina.core.foundation.report.ZafiraConfigurator</td>
	</tr>	
</table>
