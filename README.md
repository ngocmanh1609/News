# News
This is the example application about reading news in Android devices.

## In this app, we use:
### Backend (php):
+ Create mysql database with `post` table: post_id, post_title, post_thumb, post_description, category_id columns.
+ The backend site has login, logout, edit and create new post, delete post, split page, validate, create api function.
+ We connect php web to database using `medoo` lib.
+ Framework `KickStart` to design UI easier.
### Android Studio:
+ Multiscreen technique: Dimension (supported by Google), we use `SDP android` lib.
+ Menu Sliding technique: the menu table will appear when swiping from left to right.
+ Debug code with `Toast`.
+ Use `RecycleView` (AppCombat and RecycleView should be the same version.)
+ Inflater layout to throw a layout into another one.
+ Connect to database through webservice using HTTPClient such as `OKHttp`.
+ Load image from database using `Glide` lib.
+ Pull to refresh function with `SwipeRefreshLayout`.
