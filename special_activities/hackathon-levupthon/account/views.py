from django.views.generic import TemplateView
from django.views.generic.edit import CreateView
# from django.contrib.auth.forms import UserCreationForm  
from .forms import CreateUserForm , LoginForm
from django.urls import reverse_lazy 
from rest_framework.views import APIView
from django.shortcuts import render, get_object_or_404, redirect
from django.contrib.auth import login, authenticate




class SignInUserView(APIView): 
    template_name = 'signin.html'
    # form_class =  CreateUserForm 
    def post(self, request):
        form = LoginForm(request.POST)
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username = username, password = password)
        if user is not None:
            login(request, user)
            return redirect('home')
        else:
            return HttpResponse('로그인 실패. 다시 시도 해보세요.')
    def get(self, request):
        form = LoginForm()
        return render(request, 'signin.html', {'form': form})

class CreateUserView(CreateView): 
    template_name = 'signup.html'
    form_class =  CreateUserForm 
    # form_class = UserCreationForm
    success_url = reverse_lazy('signin') 

class RegisteredView(TemplateView): 
    template_name = 'registration/signup_done.html' 