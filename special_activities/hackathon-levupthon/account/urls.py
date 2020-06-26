from django.urls import path, include
from .views import CreateUserView, SignInUserView
from django.contrib.auth import views



urlpatterns = [
    path('signin', SignInUserView.as_view(), name = 'signin'),     
    path('signup', CreateUserView.as_view(), name = 'signup'),

    path('logout', views.LogoutView.as_view(), name='logout'),

]
