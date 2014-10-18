Running the app
===========

Create a jar configuration, and add the following steps to it:
- clean install (mvn)
- Build 'ican:jar' artifact

The app will regenerate the classes before each run therefore making it impossible to become a victim of an API change.

You may create separate configurations for each step, this way speeding up the re-deployments.

Contract first FTW!