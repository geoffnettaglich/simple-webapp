#How to Run

Switched over to jetty, the debug cycle is infinitely easier. Allowing you to trivially test changes, need to setup and auto reloader, but that might require java rebel or some such.

<code>
mvn compile jetty:run
</code>

I have fixed up the way we define the servlet routes so both jetty and tomcat (with a shared context, not on the root) will work now. Though in my, saem's, opinion, Jetty is the better solution

#TODO

Have jetty have different configs for prod/dev etc, show how that would be done

Got the appropriate templates loading now, can't have a template called index.jsp as that seems to hose everything, even if you put it in the WEB-INF/jsp folder, upon packaging the WAR it gets moved out, I'm guessing this is maven 'helping' me.

#Notes

The annotations and the controller routes via names can't be mixed, if you want to do controllers via names you have to implement the Controller interface, that sucks. So I opted out. Of course, I'm not big on annotations either, c'est la vie.
