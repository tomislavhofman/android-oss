package com.kickstarter.libs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

public final class KoalaEvent {
  private KoalaEvent() {}

  public static final String ACTIVITY_LOAD_MORE = "Activity Load More";
  public static final String ACTIVITY_VIEW = "Activity View";
  public static final String ACTIVITY_VIEW_ITEM = "Activity View Item";
  public static final String ADD_NEW_CARD_BUTTON_CLICKED = "Add New Card Button Clicked";
  public static final String BACK_THIS_PROJECT_BUTTON_CLICKED = "Back this Project Button Clicked";
  public static final String CANCEL_PLEDGE_BUTTON_CLICKED = "Cancel Pledge Button Clicked";
  public static final String CHANGED_EMAIL = "Changed Email";
  public static final String CHANGED_PASSWORD = "Changed Password";
  public static final String CLEARED_SEARCH_TERM = "Cleared Search Term";
  public static final String CONTINUE_USER_ACTIVITY = "Continue User Activity";
  public static final String CREATED_PASSWORD = "Created Password";
  public static final String DELETED_PAYMENT_METHOD = "Deleted Payment Method";
  public static final String DISCOVER_SEARCH_LEGACY = "Discover Search";
  public static final String DISCOVER_SEARCH_RESULTS_LEGACY = "Discover Search Results";
  public static final String DISCOVER_SEARCH_RESULTS_LOAD_MORE_LEGACY = "Discover Search Results Load More";
  public static final String ERRORED_DELETE_PAYMENT_METHOD = "Errored Delete Payment Method";
  public static final String ERRORED_USER_LOGIN = "Errored User Login";
  public static final String ERRORED_USER_SIGNUP = "Errored User Signup";
  public static final String FACEBOOK_CONFIRM = "Facebook Confirm";
  public static final String FAILED_PAYMENT_METHOD_CREATION = "Failed Payment Method Creation";
  public static final String LOADED_MORE_SEARCH_RESULTS = "Loaded More Search Results";
  public static final String LOADED_OLDER_COMMENTS = "Loaded Older Comments";
  public static final String LOADED_SEARCH_RESULTS = "Loaded Search Results";
  public static final String LOGIN = "Login";
  public static final String MANAGE_PLEDGE_BUTTON_CLICKED = "Manage Pledge Button Clicked";
  public static final String MANAGE_PLEDGE_OPTION_CLICKED = "Manage Pledge Option Clicked";
  public static final String MODAL_DIALOG_VIEW = "Modal Dialog View";
  public static final String NEW_USER = "New User";
  public static final String NOTIFICATION_OPENED_LEGACY = "Notification Opened";
  public static final String OPENED_DEEP_LINK = "Opened Deep Link";
  public static final String OPENED_EXTERNAL_LINK = "Opened External Link";
  public static final String OPENED_NOTIFICATION = "Opened Notification";
  public static final String OPENED_PROJECT_SWITCHER = "Opened Project Switcher";
  public static final String PLEDGE_BUTTON_CLICKED = "Pledge Button Clicked";
  public static final String PLEDGE_SCREEN_VIEWED = "Pledge Screen Viewed";
  public static final String POSTED_COMMENT = "Posted Comment";
  public static final String PROFILE_VIEW_MY = "Profile View My";
  public static final String PROJECT_COMMENT_CREATE = "Project Comment Create";
  public static final String PROJECT_COMMENT_LOAD_OLDER = "Project Comment Load Older";
  public static final String PROJECT_COMMENT_VIEW = "Project Comment View";
  public static final String PROJECT_PAGE = "Project Page";
  public static final String PROJECT_SHOW_SHARE_SHEET_LEGACY = "Project Show Share Sheet";
  public static final String PROJECT_STAR = "Project Star";
  public static final String PROJECT_UNSTAR = "Project Unstar";
  public static final String RESENT_VERIFICATION_EMAIL = "Resent Verification Email";
  public static final String SAVED_PAYMENT_METHOD = "Saved Payment Method";
  public static final String SELECT_REWARD_BUTTON_CLICKED = "Select Reward Button Clicked";
  public static final String SELECTED_CHOSEN_CURRENCY = "Selected Chosen Currency";
  public static final String SENT_MESSAGE = "Sent Message";
  public static final String SHOWED_SHARE_SHEET = "Showed Share Sheet";
  public static final String SIGNUP_NEWSLETTER_TOGGLE = "Signup Newsletter Toggle";
  public static final String STARRED_PROJECT = "Starred Project";
  public static final String SWITCHED_PROJECTS = "Switched Projects";
  public static final String TRIGGERED_REFRESH = "Triggered Refresh";
  public static final String TWO_FACTOR_AUTH_CONFIRM_VIEW = "Two-factor Authentication Confirm View";
  public static final String TWO_FACTOR_AUTH_RESEND_CODE = "Two-factor Authentication Resend Code";
  public static final String UNSTARRED_PROJECT = "Unstarred Project";
  public static final String UPDATE_PAYMENT_METHOD_BUTTON_CLICKED = "Update Payment Method Button Clicked";
  public static final String UPDATE_PLEDGE_BUTTON_CLICKED = "Update Pledge Button Clicked";
  public static final String USER_SIGNUP = "User Signup";
  public static final String VIEW_REWARDS_BUTTON_CLICKED = "View Rewards Button Clicked";
  public static final String VIEW_YOUR_PLEDGE_BUTTON_CLICKED = "View Your Pledge Button Clicked";
  public static final String VIEWED_ACCOUNT = "Viewed Account";
  public static final String VIEWED_ADD_NEW_CARD = "Viewed Add New Card";
  public static final String VIEWED_CHANGE_EMAIL = "Viewed Change Email";
  public static final String VIEWED_CHANGE_PASSWORD = "Viewed Change Password";
  public static final String VIEWED_COMMENTS = "Viewed Comments";
  public static final String VIEWED_CREATE_PASSWORD = "Viewed Create Password";
  public static final String VIEWED_MESSAGE_INBOX = "Viewed Message Inbox";
  public static final String VIEWED_MESSAGE_THREAD = "Viewed Message Thread";
  public static final String VIEWED_NEWSLETTER = "Viewed Newsletter";
  public static final String VIEWED_NOTIFICATIONS = "Viewed Notifications";
  public static final String VIEWED_PAYMENT_METHODS = "Viewed Payment Methods";
  public static final String VIEWED_PLEDGE_INFO = "Viewed Pledge Info";
  public static final String VIEWED_PRIVACY = "Viewed Privacy";
  public static final String VIEWED_PROFILE = "Viewed Profile";
  public static final String VIEWED_PROJECT_DASHBOARD = "Viewed Project Dashboard";
  public static final String VIEWED_SEARCH = "Viewed Search";
  public static final String VIEWED_SENT_MESSAGES = "Viewed Sent Messages";
  public static final String VIEWED_SETTINGS = "Viewed Settings";
  public static final String VIEWED_UPDATE = "Viewed Update";
  public static final String VIEWED_UPDATES = "Viewed Updates";

  @Retention(RetentionPolicy.SOURCE)
  @StringDef({BACK_THIS_PROJECT_BUTTON_CLICKED, MANAGE_PLEDGE_BUTTON_CLICKED, VIEW_REWARDS_BUTTON_CLICKED, VIEW_YOUR_PLEDGE_BUTTON_CLICKED})
  @interface ProjectAction {}
}
