# People Counter - Hinge Deliverable
## Christopher Perez


For this deliverable, I chose a MVC archiecture to separate the app into the data (model), UI (view), and logic (controller).


The data layer consists of the currentCount and totalCount variables and persist across sessions. using SharedPreferences. I considered other alternatives, like a ViewModel or Room, but this was the simplest for a lightweight app like this.

I define the view layer in activity_main.xml, which describes the layout of UI elements like the + and - buttons. In testing the app, I noticed at first how the elements along the top of the phone overlapped with the camera and other toggles. This motivated me to use android:fitsSystemWindows="true" to prevent such overlap. I also used ConstraintLayout to avoid nesting multiple LinearLayouts.

I also implement the controller logic in MainActivity.kt to handle user interactions, update our model, and trigger changes in view. All events triggered by users are handled via the setOnClickListener callbacks, which update the relevant variables and call my updateUI() function to refresh the screen. I decided to have all UI updates inside this function to avoid duplicating logic across multiple button listeners. 

Another consideration was in how we persisted state. I decided to go with onPause() to protect against process death. I chose this over onDestroy() because this is not guarenteed to be called, so onPause is more reliable.
