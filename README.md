# mvpreport
Selenium WebDriver based tool for daily effort claiming to JIRA from simple formatted text file

Short story: Our team (od application support specialist) claims work to our customer's JIRA. While avarage team member works on 5-15 tasks per day, and claiming work on JIRA is no pleasure work, here the tool become on scene.
Basic idea behind is that export and format daily effort from personal time tracker like TaskCoach into text file is significantly faster than spend 15minutes per day clicking in JIRA.

Input file format is as simple as can be:

date
task ID; time spent; <work description>
another task ID; time spent; <work description>

#empty line as day delimiter
date2
task ID; time spent; <work description>
another task ID; time spent; <work description>
