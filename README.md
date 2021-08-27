# MedicalClinic

## Note to All Students
This project was used for the final project in CSCB07 at the University of Toronto. **I have shared the GitHub link to the code with the instructors.** If you happen to come across this code, do **NOT** use this for your own project. You are free to look at the code (I cannot stop you), and learn from it but do not copy-paste any of the code here. As a piece of advice from one student to another, plagiarism is not the answer no matter how much you are struggling, the instructors will find out through their own plagiarism checks.

## Credits
This was a group project here is the contact info of my fellow collaborators and myself:
<ul>
  <li>Makrod, Saad (saad.makrod@mail.utoronto.ca)</li>
  <li>Bhattacharya, Anindro (anindro.bhattacharya@mail.utoronto.ca)</li>
  <li>Ou, Denise (denise.ou@mail.utoronto.ca)</li>
  <li>Liao, Mario (mario.liao@mail.utoronto.ca)</li>
  <li>Maqbool, Nimra (nimra.maqbool@mail.utoronto.ca)</li>
</ul>

Please vist the Individual Contributions section to see how the work was distributed between ourselves.

Furthermore, the images used were taken from Google Images.

## Necessary Software
This project was developed by using Android Studio and Firebase Realtime Database.

## Demo
A demo can be seen at https://youtu.be/gmhzkBnsJZ0. Note that there is no audio it is just a quick demo to show how to interact with the app and the database updating.

## Description
This project was developed for the final project of CSCB07 at the University of Toronto. We used Agile methodologies to develop this software, specifically we used Scrum. An in depth documentation of our Scrum meetings can be seen at "CSCB07 Project Documentations.pdf" in the repository.

When developing this app we followed user stories for both the patients and the doctors. Here are the user stories:

**Patient Goals:**
<ol> 
  <li>As a patient I want to log in so that I can see the patient screen.</li>
  <li>As a patient I want to see a list of doctors and be able to filter through them so that I can find a doctor that meets my needs.</li>
  <li>As a patient I want to be able to click on a doctor and see their availabilities so that I can book appointments and see my upcoming appointments updated accordingly.</li>
</ol>

**Doctor Goals:**
<ol> 
  <li>As a doctor I want to log in so that I can see the doctors screen.</li>
  <li>As a doctor I want to see my schedule for the week so that I can see when I have appointments.</li>
  <li>As a doctor I want to be able to see patient details when I click on an upcoming appointment.</li>
</ol>

Additionally we had a list of minimum requirements to complete. Here is the list of requirments:

**Requirements:**
<ol> 
  <li>Database of doctors: each doctor has a name, a gender (in case patients are 
	   searching for gender-specific doctors), weekly availabilities (which should 
	   change when patients book appointments), specializations (for patients looking 
	   for a specialist), and a list of patients who have visited them so far.</li>
  <li>Database of patients: each patient will have a name, a gender, date of birth, 
	   a list of previous appointments, a list of upcoming appointments, and a list 
	   of doctors at the clinic that they have previously seen.</li>
  <li>Login page for patients, and one for doctors. </li>
  <li>When patients log in they should see their upcoming appointments as well as a 
	   "book appointment" button. When they click that button, they should see a list 
	   of doctors. There is a filter option for filtering on doctors' gender and 
	   specialization. When they select a doctor, they should see the doctor's 
	   availabilities for the upcoming week. They should be able to select a timeslot 
	   and book the appointment. This action should update their upcoming appointments 
	   as well as the doctor's upcoming appointments lists.</li>
  <li>When doctors log in, they should see their upcoming appointments too. They should 
   	   be able to inspect the patients and see their info, along with a list of previous 
	   doctors this patient has seen. They should also be able to view their own schedule 
	   and see which patients have booked when, as well as which time slots are still available.</li>
  <li>When an appointment time passes, the patients' upcoming and past appointments as 
	   well as the list of doctors they have seen should be updated accordingly, and the doctor's 
	   upcoming appointments and patients list should be updated. </li>
  <li>The program must be implemented such that if any doctor leaves the clinic or new doctors 
	   join, it should be easy to include them in the scheduling system. Also, when new patients 
	   sign up to use the app, it should be easy to include them as well.</li>
</ol>

We have implemented all the features listed above.

## Individual Contributions
<table>
  <tr><th>Name</th><th>Contributions</th></tr>  
  <tr>
    <td>Saad Makrod</td>
    <td>
      <ol>
        <li>Setup the GitHub repo and added everyone as contributors</li>
        <li>Did a demo on how to use GitHub on the command line and set up the repos on your own pc</li>
        <li>Original patient app design (i.e. I made the original plan for the interfaces, abstract classes and classes (can be seen in the first commit) - we updated this as a team as we went along)
        </li>
        <li>The UIDesign and ImplementationNotes in the notes folder on github (this was only for the patients side we just followed a similar thing for the doctors, the UI design was updated to what we felt as a group worked better)
        </li>
        <li>PatientBookDoctorActivity - Allows the patients to view doctors based on the selected filters and then move to PatientDoctorAvailabilitiesActivity. Anindro showed me how to use an ArrayAdapter
        </li>
        <li>PatientDoctorAvailabilitiesActivity - Lists the selected doctor’s availabilities and when the user selects one it updates the database accordingly. It uses the patient and doctor classes developed by Mario
        </li>
        <li>DoctorAvailabilitiesActivity - I worked with Anindro and I did the addAvailabilities method, so I implemented the feature that allows doctors to add new availabilities and it updates the database accordingly
        </li>
        <li>Redid the Login Module to MVP design - Nimra implemented the original design and Anindro helped debug the issue. Nimra successfully did the MVP approach but there was an issue with the login button needing to be pressed twice before logging in. I thought of a different way to do it so I controlled Nimra’s screen via Zoom remote control and developed the Contract interface and implemented the model, view and presenter interfaces so that the Login Module followed the MVP. It seemed to work so we went with that. I later updated it so that it read data only once to avoid a recurrence of toasts
        </li>
        <li>Had meeting with Anindro and Mario to talk about the database implementation and how we wanted to structure the data (since our activities worked with it specifically)
        </li>
        <li>Tested code before pushing (as per working agreement)
        </li>
        <li>
Helped debug code in our group sessions
        </li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>Mario Liao</td>
    <td>
      <ol>
        <li>Created a UI design for the app, but did not ended up being used for final UI design for the app
        </li>
        <li>Earlier in the project, figure out the reason why we weren’t able to access (read/write) from the database which was because our rules for the firebase initially had read and write set to false
        </li>
        <li>Implemented the patient class, along with it’s update_appointment and book methods to be used in the PatientMainActivity and the PatientBookDoctorActivity. Book method will add an upcoming appointment for the patient in the database, and update_appointment method will not only remove upcoming appointments in which the data has passed, but will also send the doctors that have been visited to a list of doctors that the patient has previously visited via the database
        </li>
        <li>
Worked with Saad and Anindro to figure out how we want the database to look like and how we want the structure in the database to be (so things like where should the data for upcoming appointments be located for the patients, and what should be the key value pair be, etc)
        </li>
        <li>Implemented and created the UI for the PatientMainActivity so that it shows all the upcoming appointments for the patient (Anindro helped out here so that it shows the doctor’s name instead of username). Courtesy of Saad for the implementation of the back button in the PatientMainActivity . Also added buttons: log out (log the users out), book (brings the patient to the book activity), doctors (shows the patient the previous doctors they have already visited), refresh (refresh the PatientMainActivity activity so that it will update the upcoming appointments, if any appointments has passed, or any appointments been added, etc)
        </li>
        <li>Implemented and created the UI for PatientPreviousDoctorsActivity so that it shows the patient all the doctors they have previously visited in the clinic
        </li>
        <li>
Implemented the doctor class along with the interfaces used for it, along with it’s update_appointment and book methods to be used in DoctorMainActivity and the PatientBookDoctorActivity, so that whenever a patient books an upcoming appointment with a doctor, the doctor will also have that upcoming appointment booked for themselves, and update appointments will remove the upcoming appointments for the doctor which the date has passed and also send the patients that have been visited to a list of patients that the doctor has previously visited via the database
        </li>
        <li>Extensively tested all my code before committing and pushing to the repo
        </li>
        <li>Helped debug code in meetings
        </li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>Anindro Bhattacharya</td>
    <td>
      <ol>
        <li>Created a rough outline of the UI design for the app (which was eventually not used after a joint team consensus)
        </li>
        <li>Implemented the AppointmentMeeting and UpcomingAppointment classes which were planned to be used for updating a patient’s list of upcoming appointments. After learning more about Firebase’s asynchronous nature, our team decided to refactor the design such that the database is directly updated. Thus, these classes were no longer used in the final submission; however, logic from these implementations are integrated in the current code.
        </li>Helped Nimra push her implementations for the PatientDB, MainActivity and PatientLoginActivity classes to Github. Commit on August 1st, 2021 at 6:39 PM contains her implementations.
        <li>Implemented the FilterDoctorsActivity which allows the patient to filter the gender and specialization of a doctor in order to get a tailored list of doctors to fit their needs in the next Activity. Saad implemented the Back Button at the top of the screen to allow for the user to go back and forth between activities.
        </li>
        <li>Added the functionality in PatientDoctorAvailabilitiesActivity that after a patient books an appointment with a doctor, that appointment slot is deleted from the doctor’s list of availabilities. This is to ensure that a doctor is not double-booked.
        </li>
        <li>Implemented the backend and UI for the DoctorMainActivity which is the page that a doctor sees upon login. This activity displays a list of the doctor’s upcoming appointments in a scrollable TextView and spinner from which the user can click an appointment after which they will be directed to the ViewPatientInfoActivity. There is also the ‘Availability Manager’ button which directs the user to the DoctorAvailabilitiesActivity, the ‘Log Out’ button, the ‘Refresh’ button as well as the ‘Leave Clinic’ button which directs the user to the DeleteDoctorActivity (implemented by Denise). 
        </li>
        <li>Implemented the ViewPatientInfoActivity on which doctors can view a patient’s information (i.e., personal information and previous doctors visited) after having clicked on an upcoming appointment in the DoctorMainActivity.
        </li>
        <li>Began implementing the DoctorAvailabilitiesActivity. I helped list out the doctor’s availabilities on the screen. Saad had later added the functionality on this activity to allow the doctor to add availabilities to the list.
        </li>
        <li>Wrote all the Mockito unit test cases for the Login modules. In order to do so, I had to refactor one of the methods in the LoginData class (i.e., the Model class). These test cases have achieved 100% coverage in the LoginPresenter class.
        </li>
        <li>Helped debug code in group sessions.
        </li>
        <li>Extensively tested my code before pushing it to the repository.
        </li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>Denise Ou</td>
    <td>
      <ol>
        <li>Brainstormed and created a possible prototype for the UI design using Adobe Xd. I included images and a background (which is the current background for the app that we decided to use) to make it more realistic. Recorded short clips of navigating through the prototype to show the group during our meeting where we all shared our ideas for designs for the UI. Did not end up using my design due to complexity.
        </li>
        <li>Implemented the MasterList classes for both doctors and patients, which contained methods to add, search, and remove a doctor/patient from the master list. Originally, we planned to use the MasterList class to create a  master list to keep track of all the doctor and patient users, in order to let us quickly search for them and perform operations such as booking appointments. However, we learned that it was not possible to keep this master list constantly updated and it would be lost after the app is closed. Therefore, as a group we decided to take out the MasterList classes and not to push the code to the repository, as it was no longer needed.
        </li>
        <li>Implemented PatientSignUpActivity and created the UI for it. This activity allows patients to sign up and user input is validated through regex. It would throw errors to let the user know that they need to provide a valid input.
        </li>
        <li>After integrating the code for PatientSignUpActivity with the backend classes, there was an issue with writing to the database. Worked with Nimra to try to resolve this issue. Nimra helped to identify what was wrong and she was able to fix the code so that it was able to write to the database. Then she sent me the edited file so that I can fix the remaining issues such as the error message for a username having less than 6 characters was not showing on the emulator screen. 
        </li>
        <li>Implemented DoctorSignUpActivity and created the UI for it. This activity allows doctors to sign up and user input is validated through regex. It would throw errors to let the user know that they need to provide a valid input.
        </li>
        <li>Decided to add DoctorSelectSpecializationsActivity as the original plan was to have the doctors type in their specializations into a textbox. However, after considering possible ways to validate this input through regex, it seemed that this way was prone to spelling mistakes or doctors entering different variations of the same specializations, therefore it would be harder to validate the input.
        </li>
        <li>Implemented DoctorSelectSpecializationsActivity and created the UI for it. Created a layout with a scroll view to allow doctors to check off their specializations from a comprehensive list of specializations that I researched. Then the chosen specializations would be displayed on the DoctorSignUp screen in addition to a toast of the chosen specializations.
        </li>
        <li>I had a meeting with Anindro to debug the code together for DoctorSelectSpecializationsActivity as while I was testing my code I was not able to get to that activity through clicking the button and the toast messages were not showing up. Anindro helped and provided advice to help fix those issues, such as making sure that the activity was listed in the AndroidManifest.xml file and to delete/re-add the activity as sometimes it could be an issue with Android Studio.
        </li>
        <li>Implemented DeleteDoctorActivity and created the UI for it. This activity has two buttons that lets the doctor dismiss (if they accidentally clicked the leave clinic button) or confirm deleting their account. When the doctor deletes their account, it would delete any upcoming appointments they have on the patient’s side as well, before the doctor’s data is deleted from the database. This ensures that a patient would not have any upcoming appointments with a doctor that has left the clinic.
        </li>
        <li>Helped debug code during our group meetings.
        </li>
        <li>Thoroughly tested my code for the classes and activities that I implemented before pushing the code to the repository.
        </li>
      </ol>
    </td>
  </tr>
  <tr>
    <td>Nimra Maqbool</td>
    <td>
      <ol>
        <li>Created a rough idea of the UI design for our app (which was not used because of the complexity in my design)
        </li>
        <li>Initially implemented the PatientEntryActivity but I asked the group if I could incorporate the part on Patient LoginActivity to avoid having many activities and the group agreed to it so deleted that class.
        </li>
        <li>Implemented the PatientDB for the patient user stories (access the database to add patient/doctor and search patient/doctor in the database)
        </li>
        <li>Implemented the backend and UI of the MainActivity which is the main page of the app that shows the title and doctor and patient button.We can navigate to Doctor signin/patient sign in using the buttons
        </li>
        <li>Implemented the backend and UI of PatientLoginActivity (Added the logo, also added the password view feature that enables the user to view the inputted password. PatientLoginActivity checks for the username and password from the database and redirects you to PatientMainActivity once login is successful)
        </li>
        <li>I was having issues with pushing on git, so Anindro helped me push implementations for the PatientDB, MainActivity and PatientLoginActivity classes to Github. Commit on August 1st, 2021 at 6:39 PM contains my implementations
        </li>
        <li>PatientSignupActivity was not writing to the database, so helped Denise fix the errors with validating input and writing to the database on PatientSignupActivity (and sent the file to Denise through discord so she can continue working on her Activity)
        </li>
        <li>Implemented the DoctorDB class for the doctor user stories. (access the database to add patient/doctor and search patient/doctor in the database)
        </li>
        <li>Implemented the DoctorLoginActivity class and created the UI for it (Added the logo, password view feature. Added the logo, also added the password view feature that enables the user to view the inputted password. DoctorLoginActivity checks for the username and password from the database and redirects you to DoctorMainActivity, once login is successful)
        </li>
        <li>I Refactored the login module according to the MVP approach. I implemented the PatientLoginActivity and DoctorLoginActivity using the MVP approach. But there was an issue with the login button needing to be pressed twice before letting the user log in. Anindro and Saad tried to debug but couldn’t fix the error. Saad thought of another way to do the MVP, so he controlled my screen using the zoom remote control feature and the group decided to go with this method
        </li>
        <li>Anindro pushed my androidmanifest.xml file and MainActivity on git on Aug 8
        </li>
        <li>I forgot to push my res file that contained the doctor logo, so I sent the image to Saad, so he added it to the working copy before pushing it on github
        </li>
        <li>Helped debug code during our meetings
        </li>
        <li>Tested extensively before pushing the code to the repository
        </li>
      </ol>
    </td>
  </tr>
</table>

## How to Use
The app is straightforward to use. All you have to do is sign up and as either a Doctor or Patient and you can use the app. Please see the demo to see some functionalities of the app.

## Pictures

<img src="https://user-images.githubusercontent.com/53048085/131197853-cdd0b681-b9c0-4b03-83ce-260018f982d0.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197874-3e00c05e-cb55-437a-aeb5-2723eaf0c295.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197889-5c535e91-72a3-4a5f-9ccc-84738ed53d06.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197903-25591a0d-f24a-48f4-a3bd-088db65a2f55.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197923-2e847796-244e-44df-9e66-76a758764d08.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197944-553ce072-60ff-44dc-ab74-d2ee18345339.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197955-d958e581-324b-4427-ba97-5ab8e5236f34.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197962-b7c8658c-a122-420c-8b38-fd18704b64d1.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197971-2a623ce3-70d1-4842-9b93-d4f60dabf521.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197976-b0404f5b-9a55-4d80-bc4a-c86b900a21f4.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131197999-0e286a19-e63d-4aa7-8523-17da9a1b862e.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131198007-b7ce8052-7d35-4825-ad97-1785ba2966fb.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131198017-fae31bb4-cadd-4720-8c7d-b36713977d09.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131198037-211422a2-918e-449a-a489-bfa1031fdf3d.png" height="500">
<img src="https://user-images.githubusercontent.com/53048085/131198051-bad9ba72-27bf-48a6-a6bc-bba06943d195.png" height="500">
