# Generated by Django 2.2.4 on 2019-11-30 08:41

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('cosmetic', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='type',
            name='name',
            field=models.CharField(default='TEs\x08S', max_length=100),
            preserve_default=False,
        ),
    ]
