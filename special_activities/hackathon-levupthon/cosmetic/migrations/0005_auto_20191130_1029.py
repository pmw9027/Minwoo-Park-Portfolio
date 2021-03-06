# Generated by Django 2.2.4 on 2019-11-30 10:29

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('cosmetic', '0004_auto_20191130_1012'),
    ]

    operations = [
        migrations.AddField(
            model_name='cosmetic',
            name='description',
            field=models.TextField(default='TTT'),
            preserve_default=False,
        ),
        migrations.CreateModel(
            name='CosmeticImage',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('image', models.ImageField(upload_to='')),
                ('cosmetic', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='images', to='cosmetic.Cosmetic')),
            ],
        ),
    ]
