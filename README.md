# Lotus 
[![CircleCI](https://circleci.com/gh/vsabreu/lotus.svg?style=shield)](https://circleci.com/gh/vsabreu/lotus)

- [Overview](#overview)
- [Main Purposes](#main-purposes)
- [The Whys](#the-whys)
  * [Why Scala?](#why-scala)
  * [Why Play?](#why-play)
  * [Why "Lotus"?](#why-lotus)
- [The Application](#the-application)
  * [Running with SBT](#running-with-sbt)
  * [Running with Docker](#running-with-docker)
  * [Trying the API](#trying-the-api)
- [What is not covered?](#what-is-not-covered)

## Overview
Lotus is a solution for a backend interview test, built with Scala alongside with Play Framework. Its intent is to receive and parse a raw text body with a kart race information (pilots, laps, time, average speeds, etc) and return a series of expected results as a JSON. It counts with some interesting libraries and SBT plugins, and is also hosted on CircleCI for automatic builds.

## Main Purposes
Since the idea of the interviewer company is to adhere to a reactive, non-blocking, distributed and data-driven mindset, the purpose of this exercise is to offer alongside with the proposed problem, some solutions and insights that could be a fit for their needs.

I tried to show some important concepts for this scenario like a non-blocking API that is able to handle a big number of requests using async actions and futures, flexibility to quickly build new results using abstractions, objects and classes have single responsabilities, testable code and containerization for distribution.   

## The Whys
### Why Scala?
I've got to confess, there is a long time that I have a kind of "platonic love" with Scala. It was really great to see that the company is adopting it alongside with other technologies that I believe that are great tools for nowadays problems, so Scala was my choice to solve this test. It was a great challenge!

### Why Play?
Play is a powerful web framework that adheres to the Reactive Manifesto, so I thought it was interesting to build something based on it since being reactive is an important concept for the company. Play enable us to be really productive, so I chose it as a bonus for the proposed problem and to put in practive my studies.

### Why "Lotus"?
As the proposed problem involves racing and karts, I was inspired by old memories from watching F1 races on television. My favorite cars were the Lotus, that's why the name. As a coincidence, Lotus 72 was also considered one of the best F1 cars ever, giving this application an even more special shine!

## The Application
Lotus counts with some interesting libraries and SBT plugins, as follows:
* [SCoverage](https://github.com/scoverage/sbt-scoverage): A SBT plugin for code coverage. Gerenates interesting reports.
* [Scala-Guice](https://github.com/codingwell/scala-guice): Scala extension for Google Guice. Provides multibinding behaviour for injection and others benefits.
* [ScalaTestPlus + Play](https://github.com/playframework/scalatestplus-play): Provides integration between ScalaTestPlus and Play.
* [SBT Native Packager](https://www.scala-sbt.org/sbt-native-packager/): Enables different packaging strategies and formats, like deb and rpm packages, and many others. In Lotus, it is used to package the application as a Docker container.

To run the application, be sure that SBT and/or Docker is installed in your machine. The instructions for running the application for each scenario are described below.

### Running with SBT
First of all, certify that SBT is propertly installed. Then, clone the repository and run the application with `sbt run`. The api will be available on the default Play port 9000.

### Running with Docker
Lotus is also available as a docker image on my Docker Hub. Running the application this way is easy, just execute `docker run -d -p 9000:9000 --rm --name lotus vsabreu/lotus:1.0.0` and the application is ready to serve requests.

If you want to build a Lotus image, it is easy to do with SBT Native Packager, just run `sbt docker:publishLocal` and a new docker image will be generated in your local registry.

### Trying the API
After you have the API up and running, you can perform a POST request on `http://localhost:9000/api/v1/raceresults` via curl or some REST client like Postman or Insomnia.

Below an example of a request via curl, assuming that you have a local file with the request body (in this case, a text file called "request" that you can find on conf/resources/request):

`curl -X POST -H "Content-Type:text/plain; charset=UTF-8" --data-binary "@request" http://localhost:9000/api/v1/raceresults -v`

## What is not covered?
A lot of important concepts were not covered in this application just for the sake of simplicity and time. In a real-world application, points like logging, instrumentation for monitoring (with counters, gauges, histograms, etc), security concerns and performance would be improved.
