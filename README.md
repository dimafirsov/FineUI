# FineUI

This is my first ever project. I'm a QA and I'm trying to create a visual test framework here while learning java as the process goes.
Your eyes might hurt when you'd see the code though, but if you have a will to help me become a better developer you're always welcome to contribute.

The image creation and comparison is being made using the A-Shot library made by Yandex:
https://github.com/flyfinch/ashot
Cheer them up, they've been doing an awesome job.

What I've done so far (at least I think I've done):
 * General directory structure is created using a timestamp, Test Suite name (current class name) and Test Case name (taken from the test method name)
 * Directory structure for the screenshots (actual, expected, diff, gif)
 * Logic to create screenshots, name them properly with uniques timestamps and save them into the corresponding directory
 * Logic to make diff between actual and expected screenshots
 * Logic to run tests using several web drivers (chrome and firefox, at the moment) and save results to corresponding directories

What I plan to do:
 * Learn how to write a better code and force my brain to start finally working;
 * Implement test suites that would run several test classes at once, saving the correct output in a correct way into correct places
 * Implement the diff validation (want tests to fail when diff isn't good)
 * Add reporting and logging
 * Investigate if I dare to learn and then use Jenkins to run this shit
 * Rewrite the test cases running using multiple drivers using multithreading (omg)
 * Understand why the fuck drivers still stay in the memory and understand how to kill them dead
 *
