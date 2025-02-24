import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/user.service";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../../models/user.model";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";

@Component({
    selector: 'app-user-update',
    templateUrl: './user-update.component.html',
    styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit {
    user : User;
    updateUserForm: FormGroup;
    userId: string;
    public submitted: boolean;
    public errorMessage: string;

    constructor(private userService: UserService,
                private formBuilder: FormBuilder,
                private router: Router,
                private activatedRoute: ActivatedRoute) {
        this.user = new User();
    }

    ngOnInit() {
        this.setupUserFormControl();
        this.activatedRoute.params
            .pipe(
                map(response => {
                    return response.id;
                })
            )
            .subscribe((id: string) => {
                this.userId = id;
                this.getUserDetails();
            });
    }

    setupUserFormControl(): void {
        this.updateUserForm = this.formBuilder.group({
            scaUserData: this.formBuilder.array([
                this.initScaData()
            ]),
            email: ['', [Validators.required, Validators.email]],
            login: ['', Validators.required],
            pin: ['', [Validators.required, Validators.minLength(5)]],
        });

        this.updateUserForm.valueChanges.subscribe(val => {
            this.submitted = false;
        });
    }

    get formControl() {
        return this.updateUserForm.controls;
    }

    onSubmit() {
        this.submitted = true;
        if (this.updateUserForm.invalid) {
            this.errorMessage = 'Please verify your credentials';
            return;
        }

        const updatedUser: User = {
            ...this.user,
            email: this.updateUserForm.get('email').value,
            login: this.updateUserForm.get('login').value,
            pin: this.updateUserForm.get('pin').value,
            scaUserData: this.updateUserForm.get('scaUserData').value
        };

        this.userService.updateUserDetails(updatedUser)
            .subscribe(() => this.router.navigate(['/users/all'])
        )
    }

    initScaData() {
        return this.formBuilder.group({
            scaMethod: ['EMAIL', Validators.required],
            methodValue: ['', Validators.required],
            staticTan: [''],
            usesStaticTan: ['']
        });
    }

    getUserDetails() {
        this.userService.getUser(this.userId).subscribe((item: User) => {
            this.user = item;
            this.updateUserForm.patchValue({
                email: this.user.email,
                pin: this.user.pin,
                login: this.user.login
            });

            const scaUserData = <FormArray>this.updateUserForm.get('scaUserData');
            this.user.scaUserData.forEach((value, i) => {
                if (scaUserData.length < i + 1) {
                    scaUserData.push(this.initScaData());
                }
                scaUserData.at(i).patchValue(value);
            });
        });
    }

    addScaDataItem() {
        const control = <FormArray>this.updateUserForm.controls['scaUserData'];
        control.push(this.initScaData());
    }

    removeScaDataItem(i: number) {
        const control = <FormArray>this.updateUserForm.controls['scaUserData'];
        control.removeAt(i);
    }

}
