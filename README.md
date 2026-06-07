# FakeAesthetic - Android 2019 MVP

A 2019-style Kotlin Android application scaffold featuring Firebase authentication, Firestore integration, and Material Components.

## Features

- **Authentication**: Firebase Email/Password authentication
- **Database**: Firestore for storing posts
- **Storage**: Firebase Cloud Storage integration
- **UI**: ConstraintLayout & MotionLayout with Material Components
- **Image Loading**: Glide for efficient image caching and display
- **Architecture**: Repository pattern for data access

## Project Structure

```
app/src/main/
├── java/com/redstarkp/fakeaesthetic/
│   ├── MainActivity.kt          # Auth screen
│   ├── FeedActivity.kt          # Feed with RecyclerView
│   ├── DetailActivity.kt        # Post detail screen
│   ├── App.kt                   # Application class
│   ├── adapter/
│   │   └── FeedAdapter.kt       # RecyclerView adapter
│   ├── model/
│   │   └── Post.kt              # Data model
│   └── data/
│       └── FirebaseRepository.kt # Firestore access
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_feed.xml
│   │   ├── activity_detail.xml
│   │   └── item_feed_motion.xml
│   ├── xml/
│   │   └── scene_feed_item.xml  # MotionLayout scene
│   └── values/
│       ├── themes.xml
│       ├── colors.xml
│       └── strings.xml
└── AndroidManifest.xml
```

## Firebase Setup

### 1. Create a Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **Add Project** and follow the setup wizard
3. Name your project

### 2. Register Android App

1. In Firebase Console, click **Add app** → **Android**
2. Enter package name: `com.redstarkp.fakeaesthetic`
3. Download `google-services.json`
4. Place it in `app/` directory

### 3. Enable Authentication

1. Go to **Authentication** → **Sign-in method**
2. Enable **Email/Password** provider
3. (Optional) Add test users for development

### 4. Create Firestore Database

1. Go to **Firestore Database**
2. Click **Create Database**
3. Start in **Test Mode** (development only)
4. Choose a location close to your region
5. Create a collection named `posts`

### 5. Set Firestore Security Rules

For development (test mode), use:

```firestore
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /posts/{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

### 6. (Optional) Enable Cloud Storage

1. Go to **Storage**
2. Click **Get Started**
3. Accept default settings
4. Update security rules to allow authenticated users

## Build & Run

1. Open in Android Studio
2. Ensure `google-services.json` is in `app/` directory
3. Sync Gradle: **File** → **Sync Now**
4. Run on emulator or device: **Run** → **Run 'app'**

## Dependencies

- **Kotlin**: 1.3.72
- **AndroidX**: Core, AppCompat, ConstraintLayout
- **Material Components**: 1.2.0-alpha06
- **Firebase**: Auth 17.3.0, Firestore 21.4.3, Storage 19.1.1
- **Glide**: 4.11.0 (image loading)

## Sample Data (Firestore)

To test the feed, add documents to the `posts` collection:

```json
{
  "title": "Beautiful Sunset",
  "description": "A stunning sunset at the beach",
  "imageUrl": "https://example.com/sunset.jpg",
  "authorId": "user123",
  "createdAt": 1234567890000
}
```

## Testing

1. **Sign Up**: Create a new account with email/password
2. **View Feed**: Browse posts from Firestore
3. **View Detail**: Click a post to see full details
4. **Logout**: Sign out and return to auth screen

## Notes

- This is a 2019-style scaffold with XML layouts and traditional architecture patterns
- For production, implement proper error handling, offline support, and security rules
- MotionLayout scene is a basic template—expand with custom animations as needed
- Consider adding Paging 3, LiveData, and ViewModel for advanced features

## License

MIT License - See LICENSE file for details
