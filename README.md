# Abstractanator

#### Team 001 (444)

#### Hunter Black, Evan Johnston

## Tasks:

#### Abstractanator Application

- [X] Setup display window
- [X] Establish look and feel of application (where should things go)
- [X] Add panel to hold abstraction widgets
- [X] Add panel to hold loaded in image
- [X] Add widget for RGB abstraction
- [X] Add widget for B/W abstraction
- [X] Add widget for Geometric abstraction
- [X] Add widget for "Folding" abstraction
- [X] Complete color randomizer function
- [X] Complete black/white function
- [X] Complete folding function
- [ ] Consider other abstraction functions
- [X] Hookup interaction with RGB button to color randomizer function
- [X] Hookup interaction with BW button to b/w function
- [X] Hookup interaction with Folding button with folding function
- [X] Implement importing image
- [X] Implement exporting/saving image
- [X] History panel
- [ ] Cleanup window and make it colorful / nice looking

## Overall Process:

This document serves as an organization/vision document for the Abstractanator with VR Environment Implementation project. Our goals, roles, and tasks will outline the major features that this project will contain, with the goals containing the larger, overarching features that will be present, and the roles and tasks outlining who on our team will be responsible for creating/implementing said goals.

The timetable and implementation sections will contain the essential dates of when predetermined milestones should be complete. Additionally, the implementation section will include what specific tools, packages, and processes will be included per it’s related timetable section.

The deliverables and evaluation sections will contain the specific packages/final product that will be delivered upon the initial completion of the project, as well as any evaluations methods used to determine the successfulness of the project as a whole in regards to remaining faithful to our specified goals and associated timetable/milestones.

The risk assessment and mitigation section will contain all potential difficulties that might arise, particularly in regards to implementing the VR environment, particularly in using the Abstractanator application within the VR environment itself. Additionally, this section will outline some safety concerns that arise in regards to continual usage of a VR environment, as it is something that not many users will have been exposed to in the past.

The training and maintenance section will contain all information pertaining to introducing new users to the use of virtual reality environments and tools, as well as how to use the applications and the VR environment itself. Additionally, it will contain information on maintenance of both the hardware and software pertaining to this project.

Finally, the future directions section will outline potential future version features, as well as potential release dates.

## Goals:

For this project, our team hopes to accomplish two main overarching goals, each with a number of associated subgoals. The first of the main goals is to create an Abstractanator application, and the second being to create an associated VR environment, hereafter named AbstractanatorVR.

*Abstractanator:*
The Abstractanator application will be an image manipulation application that, in its essence, will serve to take an image and change (or abstract) certain parts of said image to the nth degree, while allowing the user to step back through each abstraction generated and take different paths as they will. There are currently three main feature sets proposed for this application.

The first main abstraction features is a color abstractor that will take the hex color values of each pixel and gradually shift that value towards either true red, true green, or true blue based off of a calculation that would determine which true color the current pixel’s hex color is closest too. The end result of this type of abstraction is to ultimately obtain an image that is composed of all true red, true green, and true blue pixels.

Secondly, there will be a black/white abstractor that will shift the color of each pixel towards either true black or true white based off of a yet-undetermined calculation to determine which true color each pixel in the original image is “closest” to. The end result of this type of abstraction is to ultimately obtain an image that is composed of all true black and true white pixels.

Third, there will be a “geometric simplification” feature that makes the shapes of images a lot more basic. For instance, an image with a lot of short, numerous, contiguous curves would be adjusted to just be one straight line. The basic idea behind this feature is that even the original image might appear like the geometric simplification the abstractanator would provide. With distance comes lost accuracy, or in the case of the abstractanator, deliberately removed accuracy.

Fourth would be a “saving” feature that would display a feed of the last 10 iterations of the abstraction or so. It would allow the user to load up any of those previous images and branch off on a new path.

Finally, there will be a folder/unfolder utility that will  serve to allow the user to fold the current image, much like you would fold a physical piece of paper, along customizable lines such that the resulting image is folded and compressed. Once folded, all future abstractions will only occur to the parts of the image that are presented on-screen. After future abstractions have occured, the image can then be unfolded, revealing the parts of the image that had since been folded and hidden from view. The end result of this type of abstraction is to obtain an image that is abstracted on varying degrees at varying locations on the image, allowing for a larger variety of abstractions to be obtained.

## Roles:
While we determined that it will be best for all of our team to share a small part of each role listed below, we felt that it would be imperative to define a number of basic roles with associated  guidelines for each of us to prescribe our workload to in order to maintain a steady flow and achieve a high level of reliability and communication between us. The major roles are listed below.

The first major role will be management, in particular time management and accountability. While working on a project with a scope of this size, it is imperative for all members of our team to stay on-task and complete all major milestones on time and to its fullest extent. In order to accomplish this to our fullest extent, we will all be responsible for completing milestones and reporting our completion of said milestones to each other as well as on our proposed timetable. This will make sure that everyone is one the same page when working through the development process.

The second major role will be through the actual development of both applications (i.e. the programming). This role encompasses all aspects associated with the actual coding of each application, independent of the implementation of said development. We have all decided that we will all work on the code for this project collaboratively rather than divide the code into sections.

The next role will be the seamless implementation of the final project. Ensuring that the features do not conflict with one another is tantamount to success.

## Milestones:
The milestones for the Abstractanator are each of the features that we intended to implement. Once again, the features will be implemented roughly in the order that they are listed on this project plan.

Holistically speaking, however, the major milestones are the first couple features from each application. If the difficulty of the latter features turns out to be more difficult than initially anticipated, then they can be sacrificed.

## Timetable:
Due to the vast modularity of our program, it is difficult to predict how long each individual feature will take to implement. However, we are tentatively dividing the project as such: the Abstractanator will be completed roughly before Spring Break, with the rest of the time spent on stretch goals and more abstraction functions. If any shift in this division occurs, it will likely be to afford more time for the latter feature, as it is expected to be more complex. That being said, below is a rough outline for deadlines of the main features of each major goal.

For the Abstractanator application, we hope finish at least one major abstraction method per 2 weeks, starting on the week of February 19th, with the possibility to have this stretch to one per every 3 weeks if we do not plan on reaching any abstraction features past those that have been defined above. The final Abstractanator application will be projected to be feature complete by early to mid April.

The final project is projected to be feature complete by the end of April, leaving a small window of time for stress testing and user testing to allow for some feedback and implementing some efficiency methods for the codebase.

## Implementation:
The Abstractanator will be done in Java, using whatever Graphics packages are necessary. However, our goal is to minimize outside sources in order to prevent conflicting packages from producing unintended results.

## Deliverables:
The final product would have the programs that deliver on the features of the Abstractanator. While ideally each feature would be intuitive enough to be understood how to use at a glance, we plan to also include a readme txt file that will fill the user in on the details of how to use each program.

## Evaluation:
Evaluating if any given feature is a success has to do with all team members collectively agreeing that a feature delivers on the “fantasy” that we agreed the feature held. We do not anticipate having to re-evaluate a feature after it is judged to be sufficient in completing its purpose, due to the modularity of the project implying that the features.

## Risk Assessment and Mitigation Strategy:
Any difficulties that arise will likely due to our collective inexperience with graphics as a whole. If our experience with graphics so far has demonstrated anything, it is that even the slightest tweak to parameters can lead to strange, incomprehensible results. So, at all times, we have to remain vigilant and make sure not to do too much at once; for every new graphical line of code, we must test its effects to see if the results are as expected.

An additional concern is the possibility that VR might cause headaches as it has done in other programs in the past. We do not find this likely to occur with our program due to its relative simplicity compared to others, but if the need arises, we will tweak the features as needed to guarantee maximum comfort.

## Training and Maintenance:
For the Abstractanator, training will occur mostly organically. Throughout the progression of the class, our knowledge of various graphical elements will grow, although for the more nuanced points of development, it will likely be necessary to peruse the API library.

Fortunately, since this program is not one that interacts with outside sources, maintenance will not really be needed. The most we would have to watch out for is if any of our resources for creating the program get updated versions, at which point we would have to decide which would be more tactical: adjusting to the new version and fixing errors that might cause, or sticking to the old one and dealing with a lack of up-to-date features.

## Future Directions:
Due to the program being modular in nature, it is easily added upon. Improvements to pre-existing features will hopefully not be necessary with either aspect of the project, as due to each features relative simplicity, the goal is to perfect them through the first pass. This leaves the future direction to be solely concerned with additional features. At the time of the plan writing, there are no current stretch goals, but that is merely because both ideas require some creative inspiration. Fortunately, if any ideas occur, they would likely be quite simple to add.
